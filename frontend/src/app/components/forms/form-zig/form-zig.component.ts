import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-form-zig',
  templateUrl: './form-zig.component.html',
  styleUrls: ['./form-zig.component.scss']
})
export class FormZigComponent {

  zigForm = this.fb.group({});
  form = new FormGroup({
    pravoPrvenstvaIOsnov: new FormControl('')
  })

  toggle = Array.from({length: 45}, () => true);
  brojPodnosiocaPrijave = [1];

  constructor(private fb: FormBuilder) {
  }

  addChildForm(name: string, group: FormGroup) {
    this.zigForm.addControl(name, group);
  }

  onSubmit() {
    this.addChildForm('pravoPrvenstvaIosnov', this.form);
    console.log(this.zigForm.value);
  }

  addNumber(i: number) {
    this.toggle = this.toggle.map((value, index) => index + 1 === i ? !value : value);
    console.log(this.toggle);
  }

  addPodnosiocPrijave(){
    if(this.brojPodnosiocaPrijave.length<5) {
      this.brojPodnosiocaPrijave.push(this.brojPodnosiocaPrijave.length+1);
    }
  }

  removePodnosiocPrijave() {
    if(this.brojPodnosiocaPrijave.length>1){
      this.brojPodnosiocaPrijave.pop();
    }
  }
}
