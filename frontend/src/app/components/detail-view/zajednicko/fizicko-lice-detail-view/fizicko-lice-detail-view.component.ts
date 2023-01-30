import { Component, Input, OnInit } from '@angular/core';
import { FizickoLice } from 'src/app/model/common/common';

@Component({
  selector: 'app-fizicko-lice-detail-view',
  templateUrl: './fizicko-lice-detail-view.component.html',
  styleUrls: ['./fizicko-lice-detail-view.component.scss']
})
export class FizickoLiceDetailViewComponent implements OnInit {
  
  @Input() lice! : FizickoLice;
  
  ngOnInit(): void {
  }

}
