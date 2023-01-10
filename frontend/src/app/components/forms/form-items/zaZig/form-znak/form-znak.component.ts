import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-znak',
  templateUrl: './form-znak.component.html',
  styleUrls: ['./form-znak.component.scss']
})
export class FormZnakComponent implements OnInit{
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    vrstaNaOsnovuIzgleda: ['individualni zig'],
    vrstaNaOsnovuKorisnika: ['verbalni zig'],
    bojaZnaka: [''],
    transliteracijaZnaka: [''],
    prevodZnaka: [''],
    opisZnaka: ['']
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }

  counter(num: number) {
    return new Array(num);
  }

  addNumber(i: number) {
    //naklik treba dodati broj u listu
    //treba promeniti boju dugmetu
    //da li dozvoliti da se predomisli i opet klikne kako bi izbacio iz liste i boja da se vrati na staro
  }
}
