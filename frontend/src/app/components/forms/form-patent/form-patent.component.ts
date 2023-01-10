import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-patent',
  templateUrl: './form-patent.component.html',
  styleUrls: ['./form-patent.component.scss']
})
export class FormPatentComponent {
  patentForm = new FormGroup({
    podnosilacPrijave: new FormControl('TFizicko_lice'),
    punomocnik: new FormControl('TPravno_lice'),
    podnosilacJePronalazac: new FormControl(false),
    pronalazacNijeNaveden: new FormControl(false),
    vrstaPrijave: new  FormControl("izdvojena")
  })

  onSubmit() {

  }
}
