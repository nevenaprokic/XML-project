import { Component, Input, OnInit } from '@angular/core';
import { Pronalazak } from 'src/app/model/patent/patent';

@Component({
  selector: 'app-pronalazak-detail-view',
  templateUrl: './pronalazak-detail-view.component.html',
  styleUrls: ['./pronalazak-detail-view.component.scss']
})
export class PronalazakDetailViewComponent implements OnInit{
  
  @Input() pronalazak! : Pronalazak;
  
  ngOnInit(): void {
    
  }

}
