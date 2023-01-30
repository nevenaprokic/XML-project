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
            console.log(response)
            const convert = require('xml-js');
            const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
            console.log(zahtevList)
            Object.entries(zahtevList).map(([key, value]) => {
              if (key.substring(4) === "Zahtev_za_priznanje_patenta"){
                console.log(zahtevList[key])
                const atrributes = zahtevList[key]._attributes;
                this.getPrefix(atrributes)
                this.convertFromJSON(zahtevList)
              }
            })
        },
        error : (error) => {
          this.toast.error(error.error.message)
        }
      })
    }
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
    //this.zahteviPatent.push(zahtevZaPriznanjePatent)
    console.log(zahtevZaPriznanjePatent)
    this.openDialg(zahtevZaPriznanjePatent)
  }

  openDialg(element: ZahtevZaPriznanjePatent){
    this.dialog.open(PatentDetailViewComponent, {
      data: element,
    });
  
  }
  

}
