import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-form-izbor-lica',
    templateUrl: './form-izbor-lica.component.html',
    styleUrls: ['./form-izbor-lica.component.scss']
})
export class FormIzborLicaComponent implements OnInit {
    @Output()
    formReady = new EventEmitter<FormGroup>();

    @Input()
    liceOdredjenje: string | undefined;

    form = this.fb.group({
        lice: ['TFizicko_lice']
    });

    liceForm = this.fb.group({});

    constructor(private fb: FormBuilder) {
    }

    ngOnInit() {
        this.liceForm.addControl('lice', this.form);
        this.formReady.emit(this.liceForm);
    }

    addChildForm(name: string, group: FormGroup) {
        this.liceForm.addControl(name, group);
    }

    emitForm() {
        this.liceForm.addControl('lice', this.form);
        this.formReady.emit(this.liceForm);
    }

}
