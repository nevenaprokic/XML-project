import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FizickoLice, PravnoLice } from 'src/app/model/common/common';
import { ZahtevZaPriznanjeZiga } from 'src/app/model/zig';
import { PrilogFromXmlService } from 'src/app/services/prilog/prilog-from-xml/prilog-from-xml.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { ZigService } from 'src/app/services/zig/zig.service';

@Component({
  selector: 'app-zig-detail-view',
  templateUrl: './zig-detail-view.component.html',
  styleUrls: ['./zig-detail-view.component.scss']
})
export class ZigDetailViewComponent {
  zahtev! : ZahtevZaPriznanjeZiga
  
  constructor(public dialogRef: MatDialogRef<ZahtevZaPriznanjeZiga>,
              @Inject(MAT_DIALOG_DATA) public data : ZahtevZaPriznanjeZiga, 
              private toast: Toastr,
              private zigService: ZigService,
              private prilogService: PrilogFromXmlService) {
      this.zahtev = data      
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
}
