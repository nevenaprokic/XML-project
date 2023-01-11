import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-fizicko-lice',
  templateUrl: './form-fizicko-lice.component.html',
  styleUrls: ['./form-fizicko-lice.component.scss']
})
export class FormFizickoLiceComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    ime: ['', [Validators.required]],
    prezime: ['', [Validators.required]]
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
