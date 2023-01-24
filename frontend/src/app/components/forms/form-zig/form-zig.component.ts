import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {MatDialog} from "@angular/material/dialog";
import {FormResenjeComponent} from "../form-resenje/form-resenje.component";
import {ZigService} from "../../../services/zig/zig.service";
import {Toastr} from "../../../services/utils/toastr/toastr.service";
import {FormConverterService} from "../../../services/zig/form-converter/form-converter.service";
import {XmlTemplateService} from "../../../services/zig/xml-template/xml-template.service";

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

    constructor(private fb: FormBuilder,
                private dialog: MatDialog,
                private templateService: XmlTemplateService,
                private zigService: ZigService,
                private toastr: Toastr,
                private formConverter: FormConverterService) {
    }

    addChildForm(name: string, group: FormGroup) {
        this.zigForm.addControl(name, group);
    }

    onSubmit() {
        this.addChildForm('pravoPrvenstvaIosnov', this.form);
        console.log(this.zigForm.value);
        const zahtevJSON = this.formConverter.convertFormToZahtev(this.zigForm, this.brojPodnosiocaPrijave.length, this.toggle);
        console.log(zahtevJSON);
        const zahtevXML = this.templateService.createNewXML(zahtevJSON);
        console.log(zahtevXML)
        // this.saveRequest(zahtevXML);
    }

    private saveRequest(xml: string) {
        this.zigService.saveNew(xml).subscribe({
            next: (documentId: string) => {
                console.log(documentId)
                this.toastr.success('Uspešno poslat zahtev za priznavanje autorskog dela')
            },
            error: (err: any) => {
                this.toastr.error('Došlo je do greško pri slanju zahteva za priznavanje autorskog dela', "Greška!")
                console.log(err)
            }
        })
    }

    addNumber(i: number) {
        this.toggle = this.toggle.map((value, index) => index + 1 === i ? !value : value);
    }

    addPodnosiocPrijave() {
        if (this.brojPodnosiocaPrijave.length < 5) {
            this.brojPodnosiocaPrijave.push(this.brojPodnosiocaPrijave.length + 1);
        }
    }

    removePodnosiocPrijave() {
        if (this.brojPodnosiocaPrijave.length > 1) {
            this.brojPodnosiocaPrijave.pop();
        }
    }

    openDialogResenje() {
        this.dialog.open(FormResenjeComponent);
    }
}
