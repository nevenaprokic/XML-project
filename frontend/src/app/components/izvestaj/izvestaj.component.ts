import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {IzvestajService} from "../../services/izvestaj/izvestaj.service";
import {Toastr} from "../../services/utils/toastr/toastr.service";
import {XmlTemplateService} from "../../services/izvestaj/xml-template/xml-template.service";

@Component({
    selector: 'app-izvestaj',
    templateUrl: './izvestaj.component.html',
    styleUrls: ['./izvestaj.component.scss']
})
export class IzvestajComponent {
    brojPodnetih: number = 10;
    brojOdbijenih: number = 5;
    brojPrihvacenih: number = 4;

    report: boolean = false;

    maxDate: Date = new Date();
    dateForm = new FormGroup({
        startDate: new FormControl<Date | null>(null),
        endDate: new FormControl<Date | null>(null),
    });

    constructor(private izvestajService:IzvestajService,
                private toastr: Toastr,
                private templateService: XmlTemplateService) {
    }


    onSubmit() {
        const izvestajXML = this.templateService.createNewXML(this.dateForm);
        this.izvestajService.getIzvestaj(izvestajXML).subscribe({
            next: (document: any) => {
                console.log(document)
                this.toastr.success('Uspešno dobavljen izveštaja')
            },
            error: (err: any) => {
                this.toastr.error('Došlo je do greško pri generisanju izveštaja', "Greška!")
                console.log(err)
            }
        })
        this.report = true;
    }

    generisiPDF() {

    }
}
