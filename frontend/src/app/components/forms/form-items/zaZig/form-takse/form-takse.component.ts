import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-takse',
  templateUrl: './form-takse.component.html',
  styleUrls: ['./form-takse.component.scss']
})
export class FormTakseComponent implements OnInit{
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    osnovnaTaksa: ['', [Validators.required]],
    zaKlasuTaksa: [''],
    zaGrafickoTaksa: ['']
  });

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}

