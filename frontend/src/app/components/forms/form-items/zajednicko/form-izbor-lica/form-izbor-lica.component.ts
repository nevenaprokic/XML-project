import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-izbor-lica',
  templateUrl: './form-izbor-lica.component.html',
  styleUrls: ['./form-izbor-lica.component.scss']
})
export class FormIzborLicaComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Input()
  liceOdredjenje: string | undefined;

  form = this.fb.group({
    lice: ['TFizicko_lice']
  });

  liceForm = this.fb.group({});

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.formReady.emit(this.liceForm);
  }

  addChildForm(name: string, group: FormGroup) {
    this.liceForm.addControl(name, group);
  }

}
