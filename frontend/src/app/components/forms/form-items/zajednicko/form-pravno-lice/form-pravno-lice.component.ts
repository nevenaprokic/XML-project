import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-pravno-lice',
  templateUrl: './form-pravno-lice.component.html',
  styleUrls: ['./form-pravno-lice.component.scss']
})
export class FormPravnoLiceComponent implements OnInit{
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    nazivPravnoLice: ['']
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
