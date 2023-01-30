

import {FormResenjeComponent} from "../../forms/form-resenje/form-resenje.component";
import {typeZahteva} from "../../../model/model";
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { environment } from 'src/app/environments/environment';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { PatentFromXmlService } from 'src/app/services/patent/patent-from-xml/patent-from-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { UserService } from 'src/app/services/user/user.service';
import { Toastr } from 'src/app/services/utils/toastr/toastr.service';
import { PatentDetailViewComponent } from '../../detail-view/patent/patent-detail-view/patent-detail-view.component';


@Component({
  selector: 'app-patent-tabel-view',
  templateUrl: './patent-tabel-view.component.html',
  styleUrls: ['./patent-tabel-view.component.scss']
})
export class PatentTabelViewComponent implements OnInit {

  zahteviPatent: ZahtevZaPriznanjePatent[] = [];
  isLoaded: boolean = false;
  displayedColumns = ["pronalazak", "podnosilac", "datum podnošenja", "prikaz"]
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaPriznanjePatent>;
  isSluzbenik: boolean = false;
  prefix: string = '';
  commonPrefix: string = '';
  isEmptySource: boolean = false


  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;


  constructor(private patentService: PatentService,
              private userService: UserService,
              private patentFromXML: PatentFromXmlService,
              private dialog: MatDialog, private toastr: Toastr) {

              }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }


  ngOnInit(): void {

    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK") {
      this.isSluzbenik = true;
      this.displayedColumns.push("rešenje")
      this.displayedColumns.push("pdf")
      this.displayedColumns.push("html")
      this.displayedColumns.push("rdf")
      this.displayedColumns.push("json")
      this.getDataForSluzbenik()
    } else {
      this.getDataForUserTabel();
    }
  }


  getDataForUserTabel() {

  }

  getDataForSluzbenik() {
    this.patentService.getAll().subscribe({
      next: (response) => {
        const convert = require('xml-js');
        const zahtevList: any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
        if (Object.keys(zahtevList.listaZahtevaPatent).length > 1) {
          const atrributes = zahtevList.listaZahtevaPatent._attributes;
          this.getPrefix(atrributes)
          this.convertFromJSON(zahtevList)
        } else {
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

  setDataSource(zahtevSource: ZahtevZaPriznanjePatent[]) {
    this.dataSource = new MatTableDataSource<ZahtevZaPriznanjePatent>(zahtevSource);
    this.dataSource.paginator = this.paginator;
  }

  sortData(sort: Sort) {
    this.setDataSource(this.patentService.sortData(sort, this.dataSource.data))
  }

  getPrefix(atrributes: any) {
    Object.entries(atrributes).map(([key, value]) => {
      if (value === environment.PATENT_NAMESPACE) {
        this.prefix = key.split(":")[1]
      }
      if (value === environment.COMMON_NAMSPACE) {
        this.commonPrefix = key.split(":")[1]
      }
    })
  }

  convertFromJSON(zahtevList: any) {
    const zahtevi: any[] = zahtevList.listaZahtevaPatent[this.prefix + ':Zahtev_za_priznanje_patenta'];
    if (Array.isArray(zahtevi)) {
      zahtevi.forEach((zahtev) => {
        let zahtevZaPriznanjePatent: ZahtevZaPriznanjePatent = this.patentFromXML.getPatentFromXML(zahtev, this.prefix, this.commonPrefix);
        console.log(zahtevZaPriznanjePatent)
        this.zahteviPatent.push(zahtevZaPriznanjePatent)
      })
    } else {
      let zahtevZaPriznanjePatent: ZahtevZaPriznanjePatent = this.patentFromXML.getPatentFromXML(zahtevi, this.prefix, this.commonPrefix);
      this.zahteviPatent.push(zahtevZaPriznanjePatent)
    }

    this.gettingDataFinished = true;
    this.setDataSource(this.zahteviPatent)
  }

  openResenje(element: ZahtevZaPriznanjePatent) {
    this.dialog.open(FormResenjeComponent, {data: {id: element.idPatenta, type: typeZahteva.PATENT}});
  }

openPatenDetailView(element: ZahtevZaPriznanjePatent){
  this.dialog.open(PatentDetailViewComponent, {
    data: element,
  });
}

}
