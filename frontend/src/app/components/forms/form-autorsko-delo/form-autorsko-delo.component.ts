import {Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import { AD_PrilogType } from 'src/app/model/model';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';
import { XMLTemplateService } from 'src/app/services/autorsko-delo/xml-template/xml-template.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';

@Component({
  selector: 'app-form-autorsko-delo',
  templateUrl: './form-autorsko-delo.component.html',
  styleUrls: ['./form-autorsko-delo.component.scss']
})
export class FormAutorskoDeloComponent {

  opisEncoded: any;
  primerEncoded: any;

  prilogTypes=AD_PrilogType

  autorskoDeloForm = this.fb.group({});
  form = new FormGroup({
    pseudonimAutora: new FormControl(''),
    naslovAutorskogDela: new FormControl(''),
    alternativniNaslovAutorskogDela: new FormControl(''),
    naslovPreradeAutorskogDela: new FormControl(''),
    vrstaAutorskogDela: new FormControl('knjizevno delo'),
    formaZapisaAutorskogDela: new FormControl('stamparski tekst'),
    podaciORadnomOdnosu: new FormControl(''),
    nacinKoriscenja: new FormControl(''),
    opis: new FormControl(),
    primer: new FormControl(),
  })
  formPodnosilac = this.fb.group({
    podnosilacPrijave: ['']
  });

  brojAutorPrerada = [1];
  brojAutori = [1];

  constructor(
    private fb: FormBuilder,
    private templateService: XMLTemplateService,
    private autorskoDeloService: AutorskoDeloService,
    private toastr: Toastr
  ) {
    // this.autorskoDeloForm.addControl("pondosilac", this.formPodnosilac)
  }

  addChildForm(name: string, group: FormGroup) {
    this.autorskoDeloForm.addControl(name, group);
  }

  onSubmit() {
    this.addChildForm('podaciOAutorskomDelu', this.form);
    console.log(this.autorskoDeloForm.value);
    const xml = this.templateService.createNewXML(this.formatData());
    this.autorskoDeloService.saveNew(xml).subscribe({
      next: (documentId: string) => {
        console.log(documentId)
        this.toastr.success('Uspešno poslat zahtev za priznavanje autorskog dela')
      },
      error: (err: any) => {
        this.toastr.error('Došlo je do greško pri slanju zahteva za priznavanje autorskog dela', "Greška!")
        console.log(err)
      }
    })
  }
  
  private formatData(): any{
    return {opis: this.opisEncoded, 
            primer: this.primerEncoded}
  }

  addAutorPrerada() {
    if (this.brojAutorPrerada.length < 5) {
      this.brojAutorPrerada.push(this.brojAutorPrerada.length + 1);
    }
  }

  removeAutorPrerada() {
    if (this.brojAutorPrerada.length > 1) {
      this.brojAutorPrerada.pop();
    }
  }

  addAutori() {
    if (this.brojAutori.length < 5) {
      this.brojAutori.push(this.brojAutori.length + 1);
    }
  }

  removeAutori() {
    if (this.brojAutori.length > 1) {
      this.brojAutori.pop();
    }
  }

  uploadPrilog(input: HTMLInputElement, prilogType: AD_PrilogType): void{
    if(input.files && input.files[0]){
        this.handleFileSelect(input.files[0], prilogType)
    }
  }

  private handleFileSelect(file: File, prilogType: AD_PrilogType){
    var reader = new FileReader();
    switch (prilogType) {
      case AD_PrilogType.OPIS:
        reader.onload = this._handleOpisReader.bind(this);
        break;
      case AD_PrilogType.PRIMER:
        reader.onload = this._handlePrimerReader.bind(this);
        break;
      default:
        break;
    }
    reader.readAsBinaryString(file);
  }

  

  private _handleOpisReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.opisEncoded = btoa(binaryString);
  }

  private _handlePrimerReader(readerEvt: any) {
    var binaryString = readerEvt.target.result;
    this.primerEncoded = btoa(binaryString);
  }


}
