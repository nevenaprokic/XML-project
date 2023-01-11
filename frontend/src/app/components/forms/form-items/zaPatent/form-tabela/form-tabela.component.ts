import {Component} from '@angular/core';

@Component({
  selector: 'app-form-tabela',
  templateUrl: './form-tabela.component.html',
  styleUrls: ['./form-tabela.component.scss']
})
export class FormTabelaComponent {
  fieldArray: Array<any> = [];
  newAttribute: any = {};

  addFieldValue() {
    this.fieldArray.push(this.newAttribute)
    this.newAttribute = {};
  }

  deleteFieldValue(index: number) {
    this.fieldArray.splice(index, 1);
  }
}
