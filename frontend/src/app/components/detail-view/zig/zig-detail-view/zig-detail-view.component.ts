import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/app/environments/environment';
import { FizickoLice, PravnoLice } from 'src/app/model/common/common';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { ZahtevZaPriznanjeZiga } from 'src/app/model/zig';
import { PrilogFromXmlService } from 'src/app/services/prilog/prilog-from-xml/prilog-from-xml.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { ZigXmlConverterService } from 'src/app/services/zig/zig-xml-converter/zig-xml-converter.service';
import { ZigService } from 'src/app/services/zig/zig.service';

@Component({
  selector: 'app-zig-detail-view',
  templateUrl: './zig-detail-view.component.html',
  styleUrls: ['./zig-detail-view.component.scss']
})
export class ZigDetailViewComponent implements OnInit {
  zahtev! : ZahtevZaPriznanjeZiga
  id!: string;
  prefix : string = ''
  commonPrefix : string = ""
  
  constructor(
              private toast: Toastr,
              private zigService: ZigService,
              private prilogService: PrilogFromXmlService,
              private routeParams: ActivatedRoute,
              private zigFromXml: ZigXmlConverterService) {     
              
               
  }
  ngOnInit(): void {
    this.routeParams.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);

      this.zigService.getById(this.id).subscribe({
        next : (response:any) => {
            let zahtev = this.getjson(response);
            Object.entries(zahtev).map(([key, value]) => {
              if (key.substring(4) === "Zahtev_za_priznanje_ziga"){
                const atrributes = zahtev[key]._attributes;
                this.getPrefix(atrributes)
                this.convertFromJSON(zahtev)
              }
            })
        },
        error : (error:any) => {
          this.toast.error(error.error.message)
        }
      })
    });
  }

  isFizickoLice(lice: FizickoLice | PravnoLice){
    return lice instanceof FizickoLice
  }

  getFizickoLice(lice : FizickoLice | PravnoLice) : FizickoLice{
    return lice as FizickoLice;
  }

  getPravnoLice(lice : FizickoLice | PravnoLice) : PravnoLice{
    return lice as PravnoLice;
  }

  prikazi(fajl: string){
    if(this.zahtev.id){
      this.zigService.getPrilog(this.zahtev.id, fajl).subscribe({
        next: (response) => {
          this.prilogService.convertToImage(response)
        },
        error: (error) => {
          this.toast.error(error.error.message)
        }
      })
    }
  }

  preuzmi(fajl: string){
    if(this.zahtev.id){
      this.zigService.getPrilog(this.zahtev.id, fajl).subscribe({
        next : (response) => {
          this.prilogService.preuzmi(response);
        },
        error : (error) => {
          this.toast.error("Nepostojeci dokument")
        }
      })
    }
    else{
      this.toast.error("Nepostojeci dokument")
    }
  }

  getjson(xml:any) : any{
    const convert = require('xml-js');
    const  zahtevList: any = JSON.parse(convert.xml2json(xml, {compact: true, spaces: 4}));
    return zahtevList;
  }

  getPrefix(atrributes: any){
    Object.entries(atrributes).map(([key, value]) => {
      if (value === environment.ZIG_NAMESPACE){
          this.prefix = key.split(":")[1]
      }
      if (value === environment.COMMON_NAMSPACE){
        this.commonPrefix = key.split(":")[1]
      }
    })
  }

  convertFromJSON(zahtevXML: any){
    const zahtev : any = zahtevXML[this.prefix + ':Zahtev_za_priznanje_ziga'];
    let zahtevZaPriznanjeZiga : ZahtevZaPriznanjeZiga = this.zigFromXml.getZigFromXML(zahtev, this.prefix, this.commonPrefix);
   this.zahtev = zahtevZaPriznanjeZiga;
  }
}
