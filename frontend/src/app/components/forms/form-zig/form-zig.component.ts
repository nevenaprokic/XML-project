import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-form-zig',
  templateUrl: './form-zig.component.html',
  styleUrls: ['./form-zig.component.scss']
})
export class FormZigComponent {

  zigForm = this.fb.group({});
  form = new FormGroup({
    pravoPrvenstvaIOsnov : new FormControl(''),
    punomocnik: new FormControl('TFizicko_lice'),
    podnosilacPrijave: new FormControl('TFizicko_lice')
  })

  constructor(private fb: FormBuilder) {
  }

  addChildForm(name: string, group: FormGroup) {
    this.zigForm.addControl(name, group);
  }

  onSubmit() {
    // const {personal, contact} = this.form.value;
    this.addChildForm('pravoPrvenstvaIosnov', this.form);
    console.log(this.zigForm.value);
  }
}
