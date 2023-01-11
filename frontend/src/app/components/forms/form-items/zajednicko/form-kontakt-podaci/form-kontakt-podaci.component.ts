import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-kontakt-podaci',
  templateUrl: './form-kontakt-podaci.component.html',
  styleUrls: ['./form-kontakt-podaci.component.scss']
})
export class FormKontaktPodaciComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    telefon: [''],
    email: [''],
    faks: ['']
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
