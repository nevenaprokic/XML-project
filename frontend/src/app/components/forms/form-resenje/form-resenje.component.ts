import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {XmlTemplateService} from "../../../services/resenje/xml-template/xml-template.service";
import {ResenjeService} from "../../../services/resenje/resenje.service";
import {Toastr} from "../../../services/utils/toastr/toastr.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {UserService} from "../../../services/user/user.service";
import {typeZahteva} from "../../../model/model";

@Component({
  selector: 'app-form-resenje',
  templateUrl: './form-resenje.component.html',
  styleUrls: ['./form-resenje.component.scss']
})
export class FormResenjeComponent implements OnInit {

  form = this.fb.group({
    resenje: ['TOdobren']
  });
  formField = new FormGroup({
    sifra: new FormControl(''),
    obrazlozenje: new FormControl('')
  });

//staviti patern i placeholder na osnovu dokumenta za koji se podnosi resenje
  placeholder: string = "";
  constructor(private fb: FormBuilder,
              private templateService: XmlTemplateService,
              private resenjeService: ResenjeService,
              private toastr: Toastr,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private userService:UserService) {
    if (data.type===typeZahteva.PATENT){
      this.formField.controls.sifra.setValidators([Validators.pattern("[P][0-9]+[/][1-2][0-9]{3,3}")]);
      this.placeholder = "P223/2003"
    }
    if (data.type===typeZahteva.ZIG){
      this.formField.controls.sifra.setValidators([Validators.pattern("\\Ž\\-[0-9]+\\/[0-9]{2,2}")]);
      this.placeholder = "Ž-912/23"
    }
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const resenjeXML = this.createXML();
    if(resenjeXML !== ""){
      this.sendResenje(resenjeXML);
    }
  }

  private createXML() {
    let resenjeXML = "";
    if (this.data.type === typeZahteva.PATENT) {
      resenjeXML = this.templateService.createNewXMLPatent(this.formField, this.form.value.resenje, this.data.id);
    } else if (this.data.type === typeZahteva.ZIG) {
      resenjeXML = this.templateService.createNewXMLZig(this.formField, this.form.value.resenje, this.data.id);
    } else if (this.data.type === typeZahteva.AUTORSKO_DELO) {
      resenjeXML = this.templateService.createNewXMLAutosrkoDelo(this.formField, this.form.value.resenje, this.data.id);
    }
    return resenjeXML;
  }

  sendResenje(xml: string) {
    this.resenjeService.sendResenje(xml, this.data.type).subscribe({
      next: (document: any) => {
        this.toastr.success('Uspešno generisan izveštaj')
      },
      error: (err: any) => {
        this.toastr.error('Došlo je do greško pri generisanju izveštaja', "Greška!")
      }
    })
  }
}
