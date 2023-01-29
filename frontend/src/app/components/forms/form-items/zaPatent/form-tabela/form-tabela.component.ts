import {Component, EventEmitter, Input, Output} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RanijaPrijava } from 'src/app/model/patent/patent';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';

@Component({
  selector: 'app-form-tabela',
  templateUrl: './form-tabela.component.html',
  styleUrls: ['./form-tabela.component.scss']
})
export class FormTabelaComponent {
  fieldArray: Array<any> = [];
  @Input() ranijePrijave: RanijaPrijava[] = [];
  newAttribute: any = {};
  
  prijavaItem = new FormGroup({
    brojPrijave: new FormControl(''),
    datumPodnosenja: new FormControl(''),
    oznakaDrzave: new FormControl(''),
  })

  constructor(private toast: Toastr){

  }

  addFieldValue() {
    console.log(this.prijavaItem)
    if (this.prijavaItem.value.oznakaDrzave && this.prijavaItem.value.brojPrijave && this.prijavaItem.value.datumPodnosenja){
      let prijava = new RanijaPrijava(this.prijavaItem.value.oznakaDrzave, this.prijavaItem.value.brojPrijave, this.prijavaItem.value.datumPodnosenja)
      this.fieldArray.push(this.newAttribute)
      this.newAttribute = {};
      this.ranijePrijave.push(prijava)
      // this.prijavaItem.controls.brojPrijave.setValue('');
      // this.prijavaItem.controls.datumPodnosenja.setValue('');
      // this.prijavaItem.controls.oznakaDrzave.setValue('');
      ;
    }
    else{
        this.toast.error("Ako unosite raniju prijavu. morate sva polja da popunite")
    }   
  }

  deleteFieldValue(index: number) {
    this.fieldArray.splice(index, 1);
  }
}
