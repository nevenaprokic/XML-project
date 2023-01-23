import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-form-resenje',
    templateUrl: './form-resenje.component.html',
    styleUrls: ['./form-resenje.component.scss']
})
export class FormResenjeComponent implements OnInit {

    form = this.fb.group({
        resenje: ['true']
    });
    formN = new FormGroup({
        sifra: new FormControl(''),
        obrazlozenje: new FormControl('')
    });
//staviti patern i placeholder na osnovu dokumenta za koji se podnosi resenje
    constructor(private fb: FormBuilder) {
    }

    ngOnInit(): void {
    }

    onSubmit() {

    }
}
