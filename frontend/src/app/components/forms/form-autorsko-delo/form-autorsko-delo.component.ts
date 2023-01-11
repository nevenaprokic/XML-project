import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-autorsko-delo',
  templateUrl: './form-autorsko-delo.component.html',
  styleUrls: ['./form-autorsko-delo.component.scss']
})
export class FormAutorskoDeloComponent {
  autorskoDeloForm = new FormGroup({
    podnosilacPrijave: new FormControl('TFizicko_lice'),
    punomocnik: new FormControl('TPravno_lice'),
    vrstaAutorskogDela: new FormControl('knjizevno delo'),
    formaZapisaAutorskogDela: new FormControl('tamparski tekst')
  })

  onSubmit() {

  }
}
