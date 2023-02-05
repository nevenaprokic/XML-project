import { P } from '@angular/cdk/keycodes';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/app/environments/environment';
import { ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';
import { ZahtevZaPriznanjeZiga } from 'src/app/model/zig';
import { AutorskoDeloXmlConvertorService } from 'src/app/services/autorsko-delo/autorsko-delo-xml-convertor/autorsko-delo-xml-convertor.service';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';
import { PrilogFromXmlService } from 'src/app/services/prilog/prilog-from-xml/prilog-from-xml.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { PrilogViewComponent } from '../../prilog-view/prilog-view.component';


@Component({
  selector: 'app-autorsko-delo-detail-view',
  templateUrl: './autorsko-delo-detail-view.component.html',
  styleUrls: ['./autorsko-delo-detail-view.component.scss']
})
export class AutorskoDeloDetailViewComponent implements OnInit {


  zahtev! : ZahtevZaAutorskoDelo;
  isAutor: boolean = false;
  isFizickoLice: boolean = false;
  isPravnoLice : boolean = false;
  pseudonim: string = ""
  id!: string;
  prefix : string = ''
  commonPrefix : string = ""

  constructor(private toast: Toastr, 
              private autorskoDeloService: AutorskoDeloService, 
              private sanitizer: DomSanitizer, 
              private dialog : MatDialog, 
              private prilogService: PrilogFromXmlService,
              private routeParams: ActivatedRoute,
              private autorskoDeloFromXml: AutorskoDeloXmlConvertorService) {
    }


ngOnInit(): void {
    this.routeParams.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);

      this.autorskoDeloService.getById(this.id).subscribe({
        next : (response:any) => {
            let zahtev = this.getjson(response);
            Object.entries(zahtev).map(([key, value]) => {
              if (key.substring(4) === "Zahtev_za_autorsko_delo"){
                const atrributes = zahtev[key]._attributes;
                this.getPrefix(atrributes)
                this.convertFromJSON(zahtev)
                this.setPodnosilac();
                


              }
            })
        },
        error : (error:any) => {
          this.toast.error(error.error.message)
        }
      })
    });
  }
  setPodnosilac() {
    this.isAutor = this.zahtev.podnosilacAutor !== undefined;
    this.isFizickoLice = this.zahtev.podnosilacFizickoLice !== undefined;
    this.isPravnoLice = this.zahtev.podnosilacPravnoLice !== undefined;
    if( this.zahtev.podnosilacAutor && this.zahtev.podnosilacAutor.pseudonim){
      this.pseudonim = this.zahtev.podnosilacAutor.pseudonim
    }
    else{
      this.zahtev.autorskoDelo.autori.forEach((autor) => {
        if(autor.primarni && autor.pseudonim){
          this.pseudonim = autor.pseudonim
        }
      })
    }
  }


  pregledaj(element:string){
    if(this.zahtev.id){
      this.autorskoDeloService.getPrilog(this.zahtev.id, element).subscribe({
        next: (response) => {
          this.prilogService.convertToImage(response)
        },
        error: (error) => {
          console.log(error)
        }
      })
    }
  }

  preuzmi(element:string){
    if(this.zahtev.id){
      this.autorskoDeloService.getPrilog(this.zahtev.id, element).subscribe({
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
      if (value === environment.AUTORSKO_DELO_NAMESPACE){
          this.prefix = key.split(":")[1]
      }
      if (value === environment.COMMON_NAMSPACE){
        this.commonPrefix = key.split(":")[1]
      }
    })
  }

  convertFromJSON(zahtevXML: any){
    const zahtev : any = zahtevXML[this.prefix + ':Zahtev_za_autorsko_delo'];
    let zahtevZaPriznanjeAutorskogDela : ZahtevZaAutorskoDelo = this.autorskoDeloFromXml.getAutoskoDeloFromXML(zahtev, this.prefix, this.commonPrefix);
   this.zahtev = zahtevZaPriznanjeAutorskogDela;
  }
}
