import { Component, Input, OnInit } from '@angular/core';
import { Podaci_o_dodatnoj_prijavi } from 'src/app/model/patent/patent';

@Component({
  selector: 'app-dopunska-prijava-detail-view',
  templateUrl: './dopunska-prijava-detail-view.component.html',
  styleUrls: ['./dopunska-prijava-detail-view.component.scss']
})
export class DopunskaPrijavaDetailViewComponent implements OnInit {
  
  @Input() dopunskaPrijava? : Podaci_o_dodatnoj_prijavi;
  brojPrijave? : string;
  tipPrijave?: string;
  datumPodnosenja?: Date;

  constructor(){}
  
  ngOnInit(): void {
    this.brojPrijave = this.dopunskaPrijava?.brojPrvobitnePrijave;
    this.tipPrijave = this.dopunskaPrijava?.tipPrijave;
    this.datumPodnosenja = this.dopunskaPrijava?.datumPodnosenjaPrvobitnePrijave;

  }

}
