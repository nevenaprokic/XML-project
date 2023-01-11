import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-znak',
  templateUrl: './form-znak.component.html',
  styleUrls: ['./form-znak.component.scss']
})
export class FormZnakComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    vrstaNaOsnovuIzgleda: ['individualni zig'],
    vrstaNaOsnovuIzgledaStr: '',
    vrstaNaOsnovuKorisnika: ['verbalni zig'],
    bojaZnaka: [''],
    transliteracijaZnaka: [''],
    prevodZnaka: [''],
    opisZnaka: ['']
  });
  @Output()
  toggleEvent = new EventEmitter<number>();

  @Input()
  toggle: Array<boolean>;

  constructor(private fb: FormBuilder) {
    this.toggle = Array.from({length: 45}, () => true);
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }

  counter(num: number) {
    return new Array(num);
  }

  addNumber(i: number) {
    this.toggleEvent.emit(i);
    this.toggle = this.toggle.map((value, index) => index + 1 === i ? !value : value);
  }
}
