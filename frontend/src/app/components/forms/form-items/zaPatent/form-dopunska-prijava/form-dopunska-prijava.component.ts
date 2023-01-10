import { Component } from '@angular/core';

@Component({
  selector: 'app-form-dopunska-prijava',
  templateUrl: './form-dopunska-prijava.component.html',
  styleUrls: ['./form-dopunska-prijava.component.scss']
})
export class FormDopunskaPrijavaComponent {
  fieldArray: Array<any> = [];
  newAttribute: any = {};

  addFieldValue() {
    this.fieldArray.push(this.newAttribute)
    this.newAttribute = {};
  }
  deleteFieldValue(index:number) {
    this.fieldArray.splice(index, 1);
  }
}
