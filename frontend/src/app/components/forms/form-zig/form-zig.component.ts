import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {MatDialog} from "@angular/material/dialog";
import {FormResenjeComponent} from "../form-resenje/form-resenje.component";
import {ZigService} from "../../../services/zig/zig.service";
import {Toastr} from "../../../services/utils/toastr/toastr.service";
import {FormConverterService} from "../../../services/zig/form-converter/form-converter.service";
import {XmlTemplateService} from "../../../services/zig/xml-template/xml-template.service";
import {PrilogImg, Z_PrilogType} from "../../../model/model";
import {TPrilozi} from 'src/app/model/zig';

@Component({
  selector: 'app-form-zig',
  templateUrl: './form-zig.component.html',
  styleUrls: ['./form-zig.component.scss']
})
export class FormZigComponent {

  primerakZnaka: PrilogImg = {content: '', name: ''};
  spisak: PrilogImg = {content: '', name: ''};
  opstiAkt: PrilogImg = {content: '', name: ''};
  pravoPrvenstva: PrilogImg = {content: '', name: ''};
  uplataTakse: PrilogImg = {content: '', name: ''};
  punomocje: PrilogImg = {content: '', name: ''};

  prilogTypes = Z_PrilogType

  zigForm = this.fb.group({});
  form = new FormGroup({
    pravoPrvenstvaIOsnov: new FormControl(''),
    primerakZnaka: new FormControl(),
    spisakRobeIUsluga: new FormControl(),
    opstiAkt: new FormControl(),
    dokazOPravuPrvenstva: new FormControl(),
    dokazOUplatiTakse: new FormControl(),
    punomocje: new FormControl(),
  })

  punomocjePrilog = new FormGroup({
    nacinPunomocja: new FormControl('Punomocje')
  })

  toggle = Array.from({length: 45}, () => true);
  brojPodnosiocaPrijave = [1];

  constructor(private fb: FormBuilder,
              private dialog: MatDialog,
              private templateService: XmlTemplateService,
              private zigService: ZigService,
              private toastr: Toastr,
              private formConverter: FormConverterService) {
  }

  addChildForm(name: string, group: FormGroup) {
    this.zigForm.addControl(name, group);
  }

  onSubmit() {
    this.addChildForm('pravoPrvenstvaIosnov', this.form);
    console.log(this.zigForm.value);
    const prilozi = this.formatPrilozi();
    if (this.punomocjePrilog.value.nacinPunomocja === "Punomocje") {
      prilozi.punomocje = {
        dostavljeno: this.punomocje.name !== '',
        putanjaDoFajla: this.formatPrilogContent(this.punomocje)
      }
    }
    if (this.punomocjePrilog.value.nacinPunomocja === "Punomocje_ranije_prilozeno") {
      prilozi.punomocjeRanijePrilozeno = {
        dostavljeno: this.punomocje.name !== '',
        putanjaDoFajla: this.formatPrilogContent(this.punomocje)
      }
    }
    if (this.punomocjePrilog.value.nacinPunomocja === "Punomocje_naknadno_dostavljeno") {
      prilozi.punomocjeNaknadnoDostavljeno = {
        dostavljeno: this.punomocje.name !== '',
        putanjaDoFajla: this.formatPrilogContent(this.punomocje)
      }
    }
    const zahtevJSON = this.formConverter.convertFormToZahtev(
      this.zigForm, this.brojPodnosiocaPrijave.length, this.toggle, prilozi
    );
    console.log(zahtevJSON);
    const zahtevXML = this.templateService.createNewXML(zahtevJSON);
    console.log(zahtevXML)
    this.saveRequest(zahtevXML);
  }

  private saveRequest(xml: string) {
    this.zigService.saveNew(xml).subscribe({
      next: (documentId: string) => {
        console.log(documentId)
        this.toastr.success('Uspešno poslat zahtev za priznavanje žiga')
      },
      error: (err: any) => {
        this.toastr.error('Došlo je do greško pri slanju zahteva za priznavanje žiga', "Greška!")
        console.log(err)
      }
    })
  }

  addNumber(i: number) {
    this.toggle = this.toggle.map((value, index) => index + 1 === i ? !value : value);
  }

  addPodnosiocPrijave() {
    if (this.brojPodnosiocaPrijave.length < 5) {
      this.brojPodnosiocaPrijave.push(this.brojPodnosiocaPrijave.length + 1);
    }
  }

  removePodnosiocPrijave() {
    if (this.brojPodnosiocaPrijave.length > 1) {
      this.brojPodnosiocaPrijave.pop();
    }
  }

  openDialogResenje() {
    this.dialog.open(FormResenjeComponent);
  }

  uploadPrilog(input: HTMLInputElement, prilogType: Z_PrilogType): void {
    if (input.files && input.files[0]) {
      this.handleFileSelect(input.files[0], prilogType)
    }
  }

  private handleFileSelect(file: File, prilogType: Z_PrilogType) {
    const reader = new FileReader();
    switch (prilogType) {
      case Z_PrilogType.PRIMERAK_ZNAKA:
        this.primerakZnaka.name = file.name
        reader.onload = this._handlePrimerakZnakaReader.bind(this);
        break;
      case Z_PrilogType.SPISAK_ROBE_I_USLUGA:
        this.spisak.name = file.name
        reader.onload = this._handleSpisakReader.bind(this);
        break;
      case Z_PrilogType.OPSTI_AKT:
        this.opstiAkt.name = file.name
        reader.onload = this._handleOpstiAktReader.bind(this);
        break;
      case Z_PrilogType.DOKAZ_O_PRAVU_PRVENSTVA:
        this.pravoPrvenstva.name = file.name
        reader.onload = this._handlePavoPrvenstvaReader.bind(this);
        break;
      case Z_PrilogType.DOKAZ_O_UPLATI_TAKSE:
        this.uplataTakse.name = file.name
        reader.onload = this._handleUplataTakseReader.bind(this);
        break;
      case Z_PrilogType.PUNOMOCJE:
        this.punomocje.name = file.name
        reader.onload = this._handlePunomocjeReader.bind(this);
        break;
      default:
        break;
    }
    reader.readAsBinaryString(file);
  }

  private _handlePrimerakZnakaReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.primerakZnaka.content = btoa(binaryString);
  }

  private _handleSpisakReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.spisak.content = btoa(binaryString);
  }

  private _handleOpstiAktReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.opstiAkt.content = btoa(binaryString);
  }

  private _handlePavoPrvenstvaReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.pravoPrvenstva.content = btoa(binaryString);
  }

  private _handleUplataTakseReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.uplataTakse.content = btoa(binaryString);
  }

  private _handlePunomocjeReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.punomocje.content = btoa(binaryString);
  }

  private formatPrilozi(): TPrilozi {
    return {
      dokazOPravuPrvenstva:
        {dostavljeno: this.pravoPrvenstva.name !== '', putanjaDoFajla: this.formatPrilogContent(this.pravoPrvenstva)},
      dokazOUplatiTakse:
        {dostavljeno: this.uplataTakse.name !== '', putanjaDoFajla: this.formatPrilogContent(this.uplataTakse)},
      opstiAktOKolektivnomZiguGarancije:
        {dostavljeno: this.opstiAkt.name !== '', putanjaDoFajla: this.formatPrilogContent(this.opstiAkt)},
      primerakZnaka:
        {dostavljeno: this.primerakZnaka.name !== '', putanjaDoFajla: this.formatPrilogContent(this.primerakZnaka)},
      spisakRobeIUsluga:
        {dostavljeno: this.spisak.name !== '', putanjaDoFajla: this.formatPrilogContent(this.spisak)},
      punomocjeNaknadnoDostavljeno: {dostavljeno: false, putanjaDoFajla: ''},
      punomocjeRanijePrilozeno: {dostavljeno: false, putanjaDoFajla: ''},
      punomocje: {dostavljeno: false, putanjaDoFajla: ''},
    }
  }

  private formatPrilogContent(prilog: PrilogImg): string {
    return prilog.name ? `${prilog.name};custom_separator;${prilog.content}` : ''
  }
}
