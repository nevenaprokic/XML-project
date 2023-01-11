import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-dopunska-prijava',
  templateUrl: './form-dopunska-prijava.component.html',
  styleUrls: ['./form-dopunska-prijava.component.scss']
})
export class FormDopunskaPrijavaComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  form = this.fb.group({
    brojPrvobitnePrijave: [''],
    datumPodnosenja: [''],
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.form);
  }
}
