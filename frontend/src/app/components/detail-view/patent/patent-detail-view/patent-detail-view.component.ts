import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FizickoLice, PravnoLice } from 'src/app/model/common/common';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
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

  ngOnInit(): void {  
    console.log(this.zahtev)
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

  constructor(public dialogRef: MatDialogRef<ZahtevZaPriznanjePatent>,
    @Inject(MAT_DIALOG_DATA) public data : ZahtevZaPriznanjePatent, private toast: Toastr) {
      this.zahtev = data
    }


}
