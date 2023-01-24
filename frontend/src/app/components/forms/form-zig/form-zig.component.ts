import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {MatDialog} from "@angular/material/dialog";
import {FormResenjeComponent} from "../form-resenje/form-resenje.component";
import {ZigService} from "../../../services/zig/zig.service";
import {Toastr} from "../../../services/utils/toastr/toastr.service";
import {FormConverterService} from "../../../services/zig/form-converter/form-converter.service";
import {XmlTemplateService} from "../../../services/zig/xml-template/xml-template.service";
import {AD_PrilogType, Z_PrilogType} from "../../../model/model";

@Component({
    selector: 'app-form-zig',
    templateUrl: './form-zig.component.html',
    styleUrls: ['./form-zig.component.scss']
})
export class FormZigComponent {
    primerakZnakaEncoded: any ='';
    spisakEncoded: any = '';
    opstiAktEncoded: any ='';
    pravoPrvenstvaEncoded: any = '';
    uplataTakseEncoded: any ='';
    punomocjeEncoded: any = '';

    prilogTypes=Z_PrilogType

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
        const zahtevJSON = this.formConverter.convertFormToZahtev(this.zigForm, this.brojPodnosiocaPrijave.length, this.toggle);
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

    uploadPrilog(input: HTMLInputElement, prilogType: Z_PrilogType): void{
        if(input.files && input.files[0]){
            this.handleFileSelect(input.files[0], prilogType)
        }
    }

    private handleFileSelect(file: File, prilogType: Z_PrilogType){
        const reader = new FileReader();
        switch (prilogType) {
            case Z_PrilogType.PRIMERAK_ZNAKA:
                reader.onload = this._handlePrimerakZnakaReader.bind(this);
                break;
            case Z_PrilogType.SPISAK_ROBE_I_USLUGA:
                reader.onload = this._handleSpisakReader.bind(this);
                break;
            case Z_PrilogType.OPSTI_AKT:
                reader.onload = this._handleOpstiAktReader.bind(this);
                break;
            case Z_PrilogType.DOKAZ_O_PRAVU_PRVENSTVA:
                reader.onload = this._handlePavoPrvenstvaReader.bind(this);
                break;
            case Z_PrilogType.DOKAZ_O_UPLATI_TAKSE:
                reader.onload = this._handleUplataTakseReader.bind(this);
                break;
            case Z_PrilogType.PUNOMOCJE:
                reader.onload = this._handlePunomocjeReader.bind(this);
                break;
            default:
                break;
        }
        reader.readAsBinaryString(file);
    }

    private _handlePrimerakZnakaReader(readerEvt: any) {
        var binaryString = readerEvt.target.result;
        this.primerakZnakaEncoded = btoa(binaryString);
    }

    private _handleSpisakReader(readerEvt: any) {
        var binaryString = readerEvt.target.result;
        this.spisakEncoded = btoa(binaryString);
    }
    private _handleOpstiAktReader(readerEvt: any) {
        var binaryString = readerEvt.target.result;
        this.opstiAktEncoded = btoa(binaryString);
    }

    private _handlePavoPrvenstvaReader(readerEvt: any) {
        var binaryString = readerEvt.target.result;
        this.pravoPrvenstvaEncoded = btoa(binaryString);
    }
    private _handleUplataTakseReader(readerEvt: any) {
        var binaryString = readerEvt.target.result;
        this.uplataTakseEncoded = btoa(binaryString);
    }

    private _handlePunomocjeReader(readerEvt: any) {
        var binaryString = readerEvt.target.result;
        this.punomocjeEncoded = btoa(binaryString);
    }
}
