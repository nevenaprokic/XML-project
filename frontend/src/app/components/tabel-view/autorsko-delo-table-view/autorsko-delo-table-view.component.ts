import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { environment } from 'src/app/environments/environment';
import { AutroskoDelo, ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';
import { AutorskoDeloXmlConvertorService } from 'src/app/services/autorsko-delo/autorsko-delo-xml-convertor/autorsko-delo-xml-convertor.service';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';
import { UserService } from 'src/app/services/user/user.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { ZigService } from 'src/app/services/zig/zig.service';

@Component({
  selector: 'app-autorsko-delo-table-view',
  templateUrl: './autorsko-delo-table-view.component.html',
  styleUrls: ['./autorsko-delo-table-view.component.scss']
})
export class AutorskoDeloTableViewComponent implements OnInit{

  zahteviPAutorskoDelo: ZahtevZaAutorskoDelo[] = [];
  isLoaded: boolean = false;
  displayedColumns = ["delo", "podnosilac", "datum podnošenja", "prikaz"]
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaAutorskoDelo>;
  isSluzbenik: boolean = false;
  prefix: string = '';
  commonPrefix : string = '';
  isEmptySource: boolean = false;

  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;
  
  constructor(private userService : UserService,public datepipe: DatePipe, private autorskoDeloService: AutorskoDeloService, 
                    private fromXMLService: AutorskoDeloXmlConvertorService, private toastr: Toastr){}

  ngOnInit(): void {
    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK"){
      this.autorskoDeloService.getAll().subscribe({
        next: (response) => {
          this.isSluzbenik = true;
          this.displayedColumns.push("rešenje")
          this.displayedColumns.push("pdf")
          this.displayedColumns.push("html")
          this.displayedColumns.push("rdf")
          this.displayedColumns.push("json")
          this.getAutorskaDelaFromResponse(response)
        },
        error: (error) => {
          console.log(error)
        }
      })
  }
  else{
    this.getDataForUserTabel();
  }
}

  getAutorskaDelaFromResponse(response: any){
    const convert = require('xml-js');
    const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
    if(Object.keys(zahtevList.listaZahtevaAutorskoDelo).length > 1){
      const atrributes = zahtevList.listaZahtevaAutorskoDelo._attributes;
      this.getPrefix(atrributes)
      this.convertFromJSON(zahtevList)
    }
    else{
      this.isEmptySource = true;
      this.gettingDataFinished = true;
      this.toastr.info("Nema odgovarajućih dokumenata")
    }
    
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

  convertFromJSON(zahtevList: any){
    const zahtevi : any[] = zahtevList.listaZahtevaAutorskoDelo[this.prefix + ':Zahtev_za_autorsko_delo'];
    if(Array.isArray(zahtevi)){
      zahtevi.forEach((zahtev) => {
        let zahtevAutorskoDelo : ZahtevZaAutorskoDelo = this.fromXMLService.getAutoskoDeloFromXML(zahtev, this.prefix, this.commonPrefix);
        this.zahteviPAutorskoDelo.push(zahtevAutorskoDelo)
      })
    }
    else{
      let zahtevAutorskoDelo : ZahtevZaAutorskoDelo = this.fromXMLService.getAutoskoDeloFromXML(zahtevi, this.prefix, this.commonPrefix);
      this.zahteviPAutorskoDelo.push(zahtevAutorskoDelo)
    }
    this.gettingDataFinished = true;
    this.setDataSource(this.zahteviPAutorskoDelo)
  }

  setDataSource(zahtevSource: ZahtevZaAutorskoDelo[]) {
    this.dataSource = new MatTableDataSource<ZahtevZaAutorskoDelo>(zahtevSource);
    this.dataSource.paginator = this.paginator;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  getDataForUserTabel(){
    this.autorskoDeloService.getAllApproved().subscribe({
      next: (response) => {
          this.getAutorskaDelaFromResponse(response)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

}
