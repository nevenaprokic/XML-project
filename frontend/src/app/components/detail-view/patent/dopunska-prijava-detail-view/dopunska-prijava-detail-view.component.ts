import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { environment } from 'src/app/environments/environment';
import { Podaci_o_dodatnoj_prijavi, ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { PatentFromXmlService } from 'src/app/services/patent/patent-from-xml/patent-from-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { PatentDetailViewComponent } from '../patent-detail-view/patent-detail-view.component';

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
  prefix: string = '';
  commonPrefix : string = '';

  constructor(private patentService: PatentService, private patentFromXML: PatentFromXmlService, public dialog: MatDialog, private toast : Toastr){}
  
  ngOnInit(): void {
    this.brojPrijave = this.dopunskaPrijava?.brojPrvobitnePrijave;
    this.tipPrijave = this.dopunskaPrijava?.tipPrijave;
    this.datumPodnosenja = this.dopunskaPrijava?.datumPodnosenjaPrvobitnePrijave;

  }

  openDocumen(){
    if(this.brojPrijave){
      this.patentService.getById(this.brojPrijave).subscribe({
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
