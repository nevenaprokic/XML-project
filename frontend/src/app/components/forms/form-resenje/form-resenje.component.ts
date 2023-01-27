import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {XmlTemplateService} from "../../../services/resenje/xml-template/xml-template.service";
import {ResenjeService} from "../../../services/resenje/resenje.service";
import {Toastr} from "../../../services/utils/toastr/toastr.service";

@Component({
  selector: 'app-form-resenje',
  templateUrl: './form-resenje.component.html',
  styleUrls: ['./form-resenje.component.scss']
})
export class FormResenjeComponent implements OnInit {

  form = this.fb.group({
    resenje: ['TOdobren']
  });
  formN = new FormGroup({
    sifra: new FormControl(''),
    obrazlozenje: new FormControl('')
  });

//staviti patern i placeholder na osnovu dokumenta za koji se podnosi resenje
  constructor(private fb: FormBuilder,
              private templateService: XmlTemplateService,
              private resenjeService: ResenjeService,
              private toastr: Toastr,) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const resenjeXML = this.templateService.createNewXML(this.formN, this.form.value.resenje,  "Z27");
    this.sendResenje(resenjeXML);
  }

  sendResenje(xml: string) {
    this.resenjeService.sendResenje(xml).subscribe({
      next: (document: any) => {
        this.toastr.success('Uspešno generisan izveštaj')
      },
      error: (err: any) => {
        this.toastr.error('Došlo je do greško pri generisanju izveštaja', "Greška!")
      }
    })
  }
}
