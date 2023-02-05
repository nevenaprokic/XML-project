import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {XmlTemplateService} from "../../../services/resenje/xml-template/xml-template.service";
import {ResenjeService} from "../../../services/resenje/resenje.service";
import {Toastr} from "../../../services/utils/toastr/toastr.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
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
  placeholder: string = "";


  constructor(private fb: FormBuilder,
              private templateService: XmlTemplateService,
              private resenjeService: ResenjeService,
              private toastr: Toastr,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private userService: UserService,
              public dialogRef: MatDialogRef<FormResenjeComponent>) {
    if (data.type === typeZahteva.ZIG) {
      this.formField.controls.sifra.setValidators([Validators.pattern("\\Ž\\-[0-9]+\\/[0-9]{2,2}")]);
      this.placeholder = "Ž-912/23"
    }
    if (this.data.type === typeZahteva.PATENT) {
        this.formField.value.sifra = this.data.id;
    }
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.form.value.resenje === "TOdobren" && this.formField.value.sifra === '') {
      return;
    }
    if (this.form.value.resenje === "TOdbijen" && this.formField.value.obrazlozenje === '' && this.formField.value.sifra === '') {
      return;
    }

    const resenjeXML = this.createXML();
    if (resenjeXML !== "") {
      this.sendResenje(resenjeXML);
    }
  }

  private createXML() {
    let resenjeXML = "";
    if (this.data.type === typeZahteva.PATENT) {
      //if (this.form.value.resenje === "TOdobren") {
        this.formField.value.sifra = this.data.id;
      //}
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
      next: (id: any) => {
        this.toastr.success('Uspešno podneto rešenje');
        
        this.dialogRef.close({data: id});
      },
      error: (err: any) => {
        console.log(err);
        this.toastr.error('Došlo je do greško podnošenju rešenja', "Greška!");
        this.dialogRef.close();
      }
    })
  }
}
