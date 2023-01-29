import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { RanijaPrijava, ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava } from 'src/app/model/patent/patent';

@Component({
  selector: 'app-ranije-prijave-detail-view',
  templateUrl: './ranije-prijave-detail-view.component.html',
  styleUrls: ['./ranije-prijave-detail-view.component.scss']
})
export class RanijePrijaveDetailViewComponent implements OnInit {
  
  @Input() ranijePrijave! : ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava;
  dataSource!: MatTableDataSource<RanijaPrijava>;
  displayedColumns = ["br.", "broj prijave", "datum podnošenja", "dvoslovna oznaka države"]
  @ViewChild(MatTable) matTable!: MatTable<any>;
  
  ngOnInit(): void {
    if(this.ranijePrijave){
      this.dataSource = new MatTableDataSource<RanijaPrijava>(this.ranijePrijave.ranijePrijave);
    }
  }

}
