import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { environment } from 'src/app/environments/environment';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { ZahtevZaPriznanjeZiga } from 'src/app/model/zig';
import { UserService } from 'src/app/services/user/user.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { ZigXmlConverterService } from 'src/app/services/zig/zig-xml-converter/zig-xml-converter.service';
import { ZigService } from 'src/app/services/zig/zig.service';

@Component({
  selector: 'app-zig-table-view',
  templateUrl: './zig-table-view.component.html',
  styleUrls: ['./zig-table-view.component.scss']
})
export class ZigTableViewComponent implements OnInit{
  
  zahteviZig: ZahtevZaPriznanjeZiga[] = [];
  isLoaded: boolean = false;
  displayedColumns = ["vrsta po korisniku", "vrsta po izgledu", "podnosilac", "datum podnošenja", "prikaz"]
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaPriznanjeZiga>;
  isSluzbenik: boolean = false;
  prefix: string = '';
  commonPrefix : string = '';
  isEmptySource : boolean = false;
  
  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;

  constructor(private zigService: ZigService, private userService: UserService, private zigFromXML: ZigXmlConverterService, private toastr: Toastr){

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  ngOnInit(): void {

    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK"){
      this.isSluzbenik = true;
      this.displayedColumns.push("rešenje")
      this.displayedColumns.push("pdf")
      this.displayedColumns.push("html")
      this.displayedColumns.push("rdf")
      this.displayedColumns.push("json")
      this.getDataForSluzbenik()
    }
    else{
      this.getDataForUserTabel();
    }
  }
  

  getDataForUserTabel(){
    this.zigService.getAllApproved().subscribe({
      next: (response) => {
          const convert = require('xml-js');
          const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4, encodeURI: "utf-8"}));
          if(Object.keys(zahtevList.listaZahtevaZiga).length > 1){
            const atrributes = zahtevList.listaZahtevaZiga._attributes;
            this.getPrefix(atrributes)
            this.convertFromJSON(zahtevList)
          }
          else{
            this.isEmptySource = true;
            this.gettingDataFinished = true;
            this.toastr.info("Nema odgovarajućih dokumenata")
          }
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  getDataForSluzbenik(){
    this.zigService.getAll().subscribe({
      next: (response) => {
          const convert = require('xml-js');
          const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4, encodeURI: "utf-8"}));
          const atrributes = zahtevList.listaZahtevaZiga._attributes;
          this.getPrefix(atrributes)
          this.convertFromJSON(zahtevList)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }
  
  setDataSource(zahtevSource: ZahtevZaPriznanjeZiga[]) {
    this.dataSource = new MatTableDataSource<ZahtevZaPriznanjeZiga>(zahtevSource);
    this.dataSource.paginator = this.paginator;
  }

  sortData(sort: Sort) {
    this.setDataSource(this.zigService.sortData(sort, this.dataSource.data))
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

  convertFromJSON(zahtevList: any){
    console.log(zahtevList)
    const zahtevi : any[] = zahtevList.listaZahtevaZiga[this.prefix + ':Zahtev_za_priznanje_ziga'];
    if(Array.isArray(zahtevi)){
      zahtevi.forEach((zahtev) => {
        let zahtevZaPriznanjeZiga : ZahtevZaPriznanjeZiga = this.zigFromXML.getZigFromXML(zahtev, this.prefix, this.commonPrefix);
        console.log(zahtevZaPriznanjeZiga)
        this.zahteviZig.push(zahtevZaPriznanjeZiga)
      })
    }else{
      console.log(zahtevi)
      let zahtevZaPriznanjeZiga : ZahtevZaPriznanjeZiga = this.zigFromXML.getZigFromXML(zahtevi, this.prefix, this.commonPrefix);
      this.zahteviZig.push(zahtevZaPriznanjeZiga)
    }
   
    this.gettingDataFinished = true;
    this.setDataSource(this.zahteviZig)
  }
}
