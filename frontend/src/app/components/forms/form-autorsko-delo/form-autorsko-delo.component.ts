import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-autorsko-delo',
  templateUrl: './form-autorsko-delo.component.html',
  styleUrls: ['./form-autorsko-delo.component.scss']
})
export class FormAutorskoDeloComponent {
  autorskoDeloForm = this.fb.group({});
  form = new FormGroup({
    pseudonimAutora: new FormControl(''),
    naslovAutorskogDela: new FormControl(''),
    alternativniNaslovAutorskogDela: new FormControl(''),
    naslovPreradeAutorskogDela: new FormControl(''),
    vrstaAutorskogDela: new FormControl('knjizevno delo'),
    formaZapisaAutorskogDela: new FormControl('stamparski tekst'),
    podaciORadnomOdnosu: new FormControl(''),
    nacinKoriscenja: new FormControl('')
  })

  formPodnosilac = this.fb.group({
    podnosilacPrijave: ['']
  });

  brojAutorPrerada = [1];
  brojAutori = [1];

  constructor(private fb: FormBuilder) {
  }

  addChildForm(name: string, group: FormGroup) {
    this.autorskoDeloForm.addControl(name, group);
  }

  onSubmit() {
    this.addChildForm('podaciOAutorskomDelu', this.form);
    console.log(this.autorskoDeloForm.value);
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

}
