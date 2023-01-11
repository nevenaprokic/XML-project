import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-patent',
  templateUrl: './form-patent.component.html',
  styleUrls: ['./form-patent.component.scss']
})
export class FormPatentComponent {

  patentForm = this.fb.group({});
  formPatentGroup = new FormGroup({
    nacinDostavljanja: new FormControl('elektronski'),
    podnosilacJePunomocnik: new FormControl('punomoćnik za zastupanje'),
    vrstaPrijave: new FormControl(''),
    podnosilacJePronalazac: new FormControl(false),
    drzavjanstvo: new FormControl(''),
    pronalazacNijeNaveden: new FormControl(false)
  })

  form = this.fb.group({
    podnosilacPrijave: ['']
  });

  toggle = Array.from({length: 45}, () => true);

  constructor(private fb: FormBuilder) {
  }

  addChildForm(name: string, group: FormGroup) {
    this.patentForm.addControl(name, group);
  }

  onSubmit() {
    this.addChildForm('patentFormParent', this.formPatentGroup);
    console.log(this.patentForm.value);
  }

}
