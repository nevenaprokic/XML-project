import { Component, Input, OnInit } from '@angular/core';
import { Autor } from 'src/app/model/autorsko-delo';

@Component({
  selector: 'app-form-autor-detail-view',
  templateUrl: './form-autor-detail-view.component.html',
  styleUrls: ['./form-autor-detail-view.component.scss']
})
export class FormAutorDetailViewComponent implements OnInit {

  @Input() lice! : Autor;

  ngOnInit(): void {
  }

}
