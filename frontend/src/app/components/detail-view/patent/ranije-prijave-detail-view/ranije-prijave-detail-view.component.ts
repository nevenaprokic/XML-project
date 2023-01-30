import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { environment } from 'src/app/environments/environment';
import { RanijaPrijava, ZahtevZaPriznanjePatent, ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava } from 'src/app/model/patent/patent';
import { PatentFromXmlService } from 'src/app/services/patent/patent-from-xml/patent-from-xml.service';
import { PatentToXmlService } from 'src/app/services/patent/patent-to-xml/patent-to-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { PatentDetailViewComponent } from '../patent-detail-view/patent-detail-view.component';

@Component({
  selector: 'app-ranije-prijave-detail-view',
  templateUrl: './ranije-prijave-detail-view.component.html',
  styleUrls: ['./ranije-prijave-detail-view.component.scss']
})
export class RanijePrijaveDetailViewComponent implements OnInit {
  
  @Input() ranijePrijave! : ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava;
  dataSource!: MatTableDataSource<RanijaPrijava>;
  displayedColumns = ["br.", "broj prijave", "datum podnošenja", "dvoslovna oznaka države", "pregledaj", "preuzmi"]
  @ViewChild(MatTable) matTable!: MatTable<any>;
  prefix : string = ''
  commonPrefix : string = ""

  constructor(private patentService: PatentService, private toast: Toastr, private patentFromXML: PatentFromXmlService, private dialog: MatDialog){}
  
  ngOnInit(): void {
    if(this.ranijePrijave){
      this.dataSource = new MatTableDataSource<RanijaPrijava>(this.ranijePrijave.ranijePrijave);
    }
  }

  preuzmiDokument(ranijaPrijava: RanijaPrijava){

  }

  pregledajDokument(ranijaPrijava : RanijaPrijava){
    if(ranijaPrijava.brojPrijave){
      this.patentService.getById(ranijaPrijava.brojPrijave).subscribe({
        next : (response) => {
            let zahtev = this.getjson(response);
            Object.entries(zahtev).map(([key, value]) => {
              if (key.substring(4) === "Zahtev_za_priznanje_patenta"){
                const atrributes = zahtev[key]._attributes;
                this.getPrefix(atrributes)
                this.convertFromJSON(zahtev)
              }
            })
        },
        error : (error) => {
          this.toast.error(error.error.message)
        }
      })
    }
  }

  getjson(xml:any) : any{
    const convert = require('xml-js');
    const  zahtevList: any = JSON.parse(convert.xml2json(xml, {compact: true, spaces: 4}));
    return zahtevList;
  }

  getPrefix(atrributes: any){
    Object.entries(atrributes).map(([key, value]) => {
      if (value === environment.PATENT_NAMESPACE){
          this.prefix = key.split(":")[1]
      }
      if (value === environment.COMMON_NAMSPACE){
        this.commonPrefix = key.split(":")[1]
      }
    })
  }

  convertFromJSON(zahtevXML: any){
    const zahtev : any = zahtevXML[this.prefix + ':Zahtev_za_priznanje_patenta'];
    let zahtevZaPriznanjePatent : ZahtevZaPriznanjePatent = this.patentFromXML.getPatentFromXML(zahtev, this.prefix, this.commonPrefix);
    this.openDialg(zahtevZaPriznanjePatent)
  }

  openDialg(element: ZahtevZaPriznanjePatent){
    this.dialog.open(PatentDetailViewComponent, {
      data: element,
    });
  }
  


}
