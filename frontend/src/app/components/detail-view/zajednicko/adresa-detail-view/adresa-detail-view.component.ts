import { Component, Input, OnInit } from '@angular/core';
import { Adresa } from 'src/app/model/common/common';

@Component({
  selector: 'app-adresa-detail-view',
  templateUrl: './adresa-detail-view.component.html',
  styleUrls: ['./adresa-detail-view.component.scss']
})
export class AdresaDetailViewComponent implements OnInit {
  
  @Input() adresa! : Adresa
  
  ngOnInit(): void {
    
  }

}
