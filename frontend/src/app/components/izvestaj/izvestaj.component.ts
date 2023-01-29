import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {IzvestajService} from "../../services/izvestaj/izvestaj.service";
import {Toastr} from "../../services/utils/toastr/toastr.service";
import {XmlTemplateService} from "../../services/izvestaj/xml-template/xml-template.service";
import {xml2js} from "xml-js";
import {Element} from "src/app/model/model";

@Component({
  selector: 'app-izvestaj',
  templateUrl: './izvestaj.component.html',
  styleUrls: ['./izvestaj.component.scss']
})
export class IzvestajComponent {
  brojPodnetih: number = 0;
  brojOdbijenih: number = 0;
  brojPrihvacenih: number = 0;

  report: boolean = false;
  izvestaj: any;

  maxDate: Date = new Date();
  dateForm = new FormGroup({
    startDate: new FormControl<Date | null>(null),
    endDate: new FormControl<Date | null>(null),
    vrstaIzvestaja: new FormControl('zig')
  });

  constructor(private izvestajService: IzvestajService,
              private toastr: Toastr,
              private templateService: XmlTemplateService) {
  }


  onSubmit() {
    const izvestajXML = this.templateService.createNewXML(this.dateForm);
    this.getIzvestaj(izvestajXML);

  }

  private getIzvestaj(izvestajXML: string) {
    console.log(izvestajXML);
    // @ts-ignore
    this.izvestajService.getIzvestaj(izvestajXML, this.dateForm.value.vrstaIzvestaja).subscribe({
      next: (document: any) => {
        this.izvestaj = document;
        this.getIzvestajFromResponse(document);
        this.toastr.success('Uspešno generisan izveštaj')
      },
      error: (err: any) => {
        this.toastr.error('Došlo je do greško pri generisanju izveštaja', "Greška!")
      }
    })
  }

  private getIzvestajFromResponse(document: any) {
    const res: Element = xml2js(document, {compact: true}) as Element;
    this.brojOdbijenih = res.Izvestaj.BrojOdbijenihZahteva._text;
    this.brojPodnetih = res.Izvestaj.BrojPodnetihZahteva._text;
    this.brojPrihvacenih = res.Izvestaj.BrojPrihvacenihZahteva._text;
    this.report = true;
  }

  generisiPDF() {
    // @ts-ignore
    this.izvestajService.getPDF(this.izvestaj, this.dateForm.value.vrstaIzvestaja).subscribe({
      next: (document: any) => {
        this.toastr.success('Uspešno skinut PDF');
      },
      error: (err: any) => {
        this.toastr.error('Došlo je do greško pri generisanju PDF-a', "Greška!")
      }
    })
  }
}
