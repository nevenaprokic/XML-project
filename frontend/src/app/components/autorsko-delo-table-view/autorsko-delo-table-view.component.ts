import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { environment } from 'src/app/environments/environment';
import { AutroskoDelo, ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';
import { AutorskoDeloXmlConvertorService } from 'src/app/services/autorsko-delo/autorsko-delo-xml-convertor/autorsko-delo-xml-convertor.service';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';
import { UserService } from 'src/app/services/user/user.service';
import { ZigService } from 'src/app/services/zig/zig.service';

@Component({
  selector: 'app-autorsko-delo-table-view',
  templateUrl: './autorsko-delo-table-view.component.html',
  styleUrls: ['./autorsko-delo-table-view.component.scss']
})
export class AutorskoDeloTableViewComponent implements OnInit{

  zahteviPAutorskoDelo: ZahtevZaAutorskoDelo[] = [];
  isLoaded: boolean = false;
  //displayedColumns = ["pronalazak", "podnosilac", "datum podnošenja", "prikaz"]
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaAutorskoDelo>;
  isSluzbenik: boolean = false;
  prefix: string = '';
  commonPrefix : string = '';

  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;
  
  constructor(private userService : UserService, private autorskoDeloService: AutorskoDeloService, private fromXMLService: AutorskoDeloXmlConvertorService){}

  ngOnInit(): void {
    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK"){
      this.autorskoDeloService.getAll().subscribe({
        next: (response) => {
          this.getAutorskaDelaFromResponse(response)
        },
        error: (error) => {
          console.log(error)
        }
      })
  }}

  getAutorskaDelaFromResponse(response: any){
    const convert = require('xml-js');
    const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
    console.log(zahtevList)
    const atrributes = zahtevList.listaZahtevaAutorskoDelo._attributes;
    this.getPrefix(atrributes)
    this.convertFromJSON(zahtevList)
    //const zahtevi : any[] = zahtevList.listaZahtevaPatent[prefix + ':Zahtev_za_priznanje_patenta'];
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
    console.log(this.prefix)
    const zahtevi : any[] = zahtevList.listaZahtevaAutorskoDelo[this.prefix + ':Zahtev_za_autorsko_delo'];
    console.log(zahtevi)
    zahtevi.forEach((zahtev) => {
      let zahtevAutorskoDelo : ZahtevZaAutorskoDelo = this.fromXMLService.getAutoskoDeloFromXML(zahtev, this.prefix, this.commonPrefix);
      console.log("aaa", zahtevAutorskoDelo)
      this.zahteviPAutorskoDelo.push(zahtevAutorskoDelo)
    })
    this.gettingDataFinished = true;
    this.setDataSource(this.zahteviPAutorskoDelo)
  }

  setDataSource(zahtevSource: ZahtevZaAutorskoDelo[]) {
    this.dataSource = new MatTableDataSource<ZahtevZaAutorskoDelo>(zahtevSource);
    this.dataSource.paginator = this.paginator;
  }


}
