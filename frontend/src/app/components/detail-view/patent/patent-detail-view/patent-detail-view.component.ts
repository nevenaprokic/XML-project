import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/app/environments/environment';
import { FizickoLice, PravnoLice } from 'src/app/model/common/common';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { ZahtevZaPriznanjeZiga } from 'src/app/model/zig';
import { PatentFromXmlService } from 'src/app/services/patent/patent-from-xml/patent-from-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';

@Component({
  selector: 'app-patent-detail-view',
  templateUrl: './patent-detail-view.component.html',
  styleUrls: ['./patent-detail-view.component.scss']
})
export class PatentDetailViewComponent implements OnInit{

  zahtev! : ZahtevZaPriznanjePatent
  isPodnosilazFizickoLice: boolean = false;
  podnosilacFizickoLice?: FizickoLice;
  podnosilacPravnoLice?: PravnoLice;
  isPunomocnikFizickoLice: boolean = false;
  punomocnikFizickoLice?: FizickoLice;
  punomocnikPravnoLice?: PravnoLice;
  id! : string;
  prefix : string = ''
  commonPrefix : string = ""

  constructor( private toast: Toastr, 
          private patentService: PatentService, 
          private patentFromXml: PatentFromXmlService,
          private routeParams: ActivatedRoute,) {
 
    }

  ngOnInit(): void {  
    this.routeParams.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);

      this.patentService.getById(this.id).subscribe({
        next : (response:any) => {
            let zahtev = this.getjson(response);
            Object.entries(zahtev).map(([key, value]) => {
              if (key.substring(4) === "Zahtev_za_priznanje_patenta"){
                const atrributes = zahtev[key]._attributes;
                this.getPrefix(atrributes)
                this.convertFromJSON(zahtev)
                this.setPodnosilac()
              }
            })
        },
        error : (error:any) => {
          this.toast.error(error.error.message)
        }
      })
    });
    
  }

  setPodnosilac(){
    this.isPodnosilazFizickoLice = this.zahtev.podnosilac.lice instanceof FizickoLice
    if (this.zahtev.podnosilac.lice instanceof FizickoLice) {
      this.podnosilacFizickoLice = this.zahtev.podnosilac.lice
    }
    else {
      this.podnosilacPravnoLice = this.zahtev.podnosilac.lice
    }

    this.isPunomocnikFizickoLice = this.zahtev.punomocnik?.lice instanceof FizickoLice
    if (this.zahtev.punomocnik?.lice instanceof FizickoLice) {
      this.punomocnikFizickoLice = this.zahtev.punomocnik.lice
    }
    else {
      this.punomocnikPravnoLice = this.zahtev.punomocnik?.lice
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
      let zahtevZaPriznanjePatent : ZahtevZaPriznanjePatent = this.patentFromXml.getPatentFromXML(zahtev, this.prefix, this.commonPrefix);
     this.zahtev = zahtevZaPriznanjePatent;
    }


}
