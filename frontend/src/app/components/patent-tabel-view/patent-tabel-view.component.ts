import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { PatentFromXmlService } from 'src/app/services/patent/patent-from-xml/patent-from-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-patent-tabel-view',
  templateUrl: './patent-tabel-view.component.html',
  styleUrls: ['./patent-tabel-view.component.scss']
})
export class PatentTabelViewComponent implements OnInit{

  zahteviPatent: ZahtevZaPriznanjePatent[] = [];
  isLoaded: boolean = false;
  displayedColumns = ["pronalazak", "podnosilac", "datum podnošenja", "prikaz"]
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaPriznanjePatent>;
  isSluzbenik: boolean = false;


  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;
  
  constructor(private patentService: PatentService, private userService: UserService, private patentFromXML: PatentFromXmlService){

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

  }

  getDataForSluzbenik(){
    this.patentService.getAll().subscribe({
      next: (response) => {
          const convert = require('xml-js');
          const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
          const zahtevi : any[] = zahtevList.patentList['ns5:Zahtev_za_priznanje_patenta'];
          zahtevi.forEach((zahtev) => {
            let zahtevZaPriznanjePatent : ZahtevZaPriznanjePatent = this.patentFromXML.getPatentFromXML(zahtev);
            console.log(zahtevZaPriznanjePatent)
            this.zahteviPatent.push(zahtevZaPriznanjePatent)
          })
          this.gettingDataFinished = true;
          this.setDataSource(this.zahteviPatent)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }
  
  setDataSource(zahtevSource: ZahtevZaPriznanjePatent[]) {
    this.dataSource = new MatTableDataSource<ZahtevZaPriznanjePatent>(zahtevSource);
    this.dataSource.paginator = this.paginator;
  }

  sortData(sort: Sort) {
    this.setDataSource(this.patentService.sortData(sort, this.dataSource.data))
  }
}
