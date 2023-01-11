import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-adresa',
  templateUrl: './form-adresa.component.html',
  styleUrls: ['./form-adresa.component.scss']
})
export class FormAdresaComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Input()
  drzava!:boolean;

  form = this.fb.group({
    ulica: ['', [Validators.required]],
    broj: [''],
    grad: ['', [Validators.required]],
    postanskiBroj: [''],
    drzava: ['']
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
