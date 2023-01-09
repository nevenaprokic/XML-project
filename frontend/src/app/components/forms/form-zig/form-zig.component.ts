import { Component } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-form-zig',
  templateUrl: './form-zig.component.html',
  styleUrls: ['./form-zig.component.scss']
})
export class FormZigComponent {

  zigForm = new FormGroup({
    podnosilacPrijave: new FormControl('TFizicko_lice'),
    punomocnik: new FormControl('TPravno_lice')
  })

}
