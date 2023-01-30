import { Component, Input, OnInit } from '@angular/core';
import { KontaktPodaci } from 'src/app/model/common/common';

@Component({
  selector: 'app-kontakt-detail-view',
  templateUrl: './kontakt-detail-view.component.html',
  styleUrls: ['./kontakt-detail-view.component.scss']
})
export class KontaktDetailViewComponent implements OnInit{
  
  @Input() kontakt!: KontaktPodaci
  
  ngOnInit(): void {
  }

}
