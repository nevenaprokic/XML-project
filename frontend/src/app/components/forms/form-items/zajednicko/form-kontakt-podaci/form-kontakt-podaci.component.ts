import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-kontakt-podaci',
  templateUrl: './form-kontakt-podaci.component.html',
  styleUrls: ['./form-kontakt-podaci.component.scss']
})
export class FormKontaktPodaciComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Input()
  faks!: boolean;

  form = this.fb.group({
    telefon: [''],
    email: ['', [Validators.required]],
    faks: ['']
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }

  emitForm() {
    this.formReady.emit(this.form);
  }
}
