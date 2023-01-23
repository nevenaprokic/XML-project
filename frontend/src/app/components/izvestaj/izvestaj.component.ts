import { Component } from '@angular/core';

@Component({
  selector: 'app-izvestaj',
  templateUrl: './izvestaj.component.html',
  styleUrls: ['./izvestaj.component.scss']
})
export class IzvestajComponent {
    brojPodnetih: number = 10;
    brojOdbijenih: number = 5;
    brojPrihvacenih: number = 4;

    onSubmit() {

    }

    generisiPDF() {

    }
}
