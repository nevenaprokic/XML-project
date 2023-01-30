import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import { RanijaPrijava, ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { PatentToXmlService } from 'src/app/services/patent/patent-to-xml/patent-to-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { PatentXmlTemplateService } from 'src/app/services/patent/xml-template/patent-xml-template.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';

@Component({
  selector: 'app-form-patent',
  templateUrl: './form-patent.component.html',
  styleUrls: ['./form-patent.component.scss']
})
export class FormPatentComponent {

  ranijePrijave : RanijaPrijava[] = [];

  patentForm = this.fb.group({});
  formPatentGroup = new FormGroup({
    nacinDostavljanja: new FormControl('elektronski'),
    podnosilacJePunomocnik: new FormControl(''),
    vrstaPrijave: new FormControl(''),
    podnosilacJePronalazac: new FormControl(false),
    drzavjanstvo: new FormControl(''),
    pronalazacNijeNaveden: new FormControl(false)
  })

  form = this.fb.group({
    podnosilacPrijave: ['']
  });

  toggle = Array.from({length: 45}, () => true);

  constructor(private fb: FormBuilder, private toatr : Toastr, private paatentToXML : PatentToXmlService, private patentXMLTempalte: PatentXmlTemplateService,
    private patentService: PatentService) {
  }

  addChildForm(name: string, group: FormGroup) {
    this.patentForm.addControl(name, group);
  }

  onSubmit() {
    this.addChildForm('patentFormParent', this.formPatentGroup);
    this.addChildForm('zahtevZaPrvenstvo', new FormGroup({prijave: new FormControl(this.ranijePrijave)}))
    console.log(this.patentForm.value);
    let zahtev : ZahtevZaPriznanjePatent = this.paatentToXML.converFormToObject(this.patentForm.value);
    let xml : string = this.patentXMLTempalte.createNewXML(zahtev);
    this.patentService.saveNew(xml).subscribe({
      next : (response) => {
        this.toatr.success(response.text)
      },
      error : (error) => {
        this.toatr.error(error.error.message)
      }
    })
  }

}
