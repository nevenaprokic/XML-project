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
  brojAutorPrerada = [1];
  brojAutori = [1];

  onSubmit() {

  }

  addAutorPrerada(){
    if(this.brojAutorPrerada.length<5) {
      this.brojAutorPrerada.push(this.brojAutorPrerada.length+1);
    }
  }

  removeAutorPrerada() {
    if(this.brojAutorPrerada.length>1){
      this.brojAutorPrerada.pop();
    }
  }
  addAutori(){
    if(this.brojAutori.length<5) {
      this.brojAutori.push(this.brojAutori.length+1);
    }
  }

  removeAutori() {
    if(this.brojAutori.length>1){
      this.brojAutori.pop();
    }
  }

}
