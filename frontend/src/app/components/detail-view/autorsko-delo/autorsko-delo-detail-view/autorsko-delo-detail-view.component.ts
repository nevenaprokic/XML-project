import { P } from '@angular/cdk/keycodes';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';
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

  constructor(public dialogRef: MatDialogRef<ZahtevZaAutorskoDelo>,
    @Inject(MAT_DIALOG_DATA) public data : ZahtevZaAutorskoDelo, private toast: Toastr, private autorskoDeloService: AutorskoDeloService, 
    private sanitizer: DomSanitizer, private dialog : MatDialog, private prilogService: PrilogFromXmlService) {

      this.zahtev = data
      console.log(this.zahtev)
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


  ngOnInit(): void {
   
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
      this.prilogService.preuzmi(this.zahtev.id, element)
    }
    else{
      this.toast.error("Nepostojeci dokument")
    }
   
  }

  
}
