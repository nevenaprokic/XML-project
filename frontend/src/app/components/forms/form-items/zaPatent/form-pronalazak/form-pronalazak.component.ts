import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-pronalazak',
  templateUrl: './form-pronalazak.component.html',
  styleUrls: ['./form-pronalazak.component.scss']
})
export class FormPronalazakComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    pronalazakNaSrpskom: ['', [Validators.required]],
    pronalazakNaEngleskom: ['', [Validators.required]],
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
