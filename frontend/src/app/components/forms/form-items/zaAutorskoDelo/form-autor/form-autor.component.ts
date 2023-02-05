import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-form-autor',
  templateUrl: './form-autor.component.html',
  styleUrls: ['./form-autor.component.scss']
})
export class FormAutorComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    pseudonim:[''],
    godinaSmrti: [''],
    primarniAutor: true,
    anonimniAutor: false
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }

  emitForm(){
    this.formReady.emit(this.form);
  }
}
