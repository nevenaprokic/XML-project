import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-adresa',
  templateUrl: './form-adresa.component.html',
  styleUrls: ['./form-adresa.component.scss']
})
export class FormAdresaComponent implements OnInit{
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    ulica: ['', [Validators.required]],
    broj: [''],
    grad: ['', [Validators.required]],
    postanskiBroj:[''],
    drzava:['']
  });

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
