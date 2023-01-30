import {DatePipe} from '@angular/common';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {environment} from 'src/app/environments/environment';
import {ZahtevZaAutorskoDelo} from 'src/app/model/autorsko-delo';
import {
  AutorskoDeloXmlConvertorService
} from 'src/app/services/autorsko-delo/autorsko-delo-xml-convertor/autorsko-delo-xml-convertor.service';
import {AutorskoDeloService} from 'src/app/services/autorsko-delo/autorsko-delo.service';
import {UserService} from 'src/app/services/user/user.service';
import {Toastr} from 'src/app/services/utils/toastr/toastr.service';
import {ZahtevZaPriznanjeZiga} from "../../../model/zig";
import {FormResenjeComponent} from "../../forms/form-resenje/form-resenje.component";
import {typeZahteva} from "../../../model/model";
import {MatDialog} from "@angular/material/dialog";
import { AutorskoDeloDetailViewComponent } from '../../detail-view/autorsko-delo/autorsko-delo-detail-view/autorsko-delo-detail-view.component';
import { FileUtilService } from 'src/app/services/utils/file-util/file-util.service';

@Component({
  selector: 'app-autorsko-delo-table-view',
  templateUrl: './autorsko-delo-table-view.component.html',
  styleUrls: ['./autorsko-delo-table-view.component.scss']
})
export class AutorskoDeloTableViewComponent implements OnInit {

  zahteviPAutorskoDelo: ZahtevZaAutorskoDelo[] = [];
  isLoaded: boolean = false;
  basicColoumns = ["delo", "podnosilac", "datum podnošenja", "prikaz"]
  displayedColumns: string[] = []
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaAutorskoDelo>;
  isSluzbenik: boolean = false;
  prefix: string = '';
  commonPrefix: string = '';
  isEmptySource: boolean = false;

  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;
  
  metadataOptions = [    
    'datum_podnosenja', 
    'autorsko_delo', 
    'primarni_autor',
    'koautor',
    'ime_prodnosioca',
    'prilog'
  ]

  constructor(private userService: UserService,
              public datepipe: DatePipe,
              private autorskoDeloService: AutorskoDeloService,
              private fromXMLService: AutorskoDeloXmlConvertorService,
              private toastr: Toastr,
              private dialog: MatDialog,
              private fileUtils: FileUtilService) {
  }

  ngOnInit(): void {
    this.getDataByRole();
  }

  getDataByRole(){
    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK") {
      this.isSluzbenik = true;
      this.setDisplayedColumnsForSluzbenik()
      this.getDataForSluzbenik()
    } else {
      this.getDataForUserTabel();
    }
  }
  setDisplayedColumnsForSluzbenik(): void {
    this.displayedColumns = [...this.basicColoumns, "rešenje",  "pdf", "html", "rdf", "json"]
  }

  getAutorskaDelaFromResponse(response: any) {
    this.zahteviPAutorskoDelo = []
    const convert = require('xml-js');
    const zahtevList: any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
    if (zahtevList.listaZahtevaAutorskoDelo && Object.keys(zahtevList.listaZahtevaAutorskoDelo).length > 1) {
      const atrributes = zahtevList.listaZahtevaAutorskoDelo._attributes;
      this.getPrefix(atrributes)
      this.convertFromJSON(zahtevList)
      this.isEmptySource = false;
    } else {
      this.isEmptySource = true;
      this.gettingDataFinished = true;
      this.toastr.info("Nema odgovarajućih dokumenata")
    }

  }

  getPrefix(atrributes: any) {
    Object.entries(atrributes).map(([key, value]) => {
      if (value === environment.AUTORSKO_DELO_NAMESPACE) {
        this.prefix = key.split(":")[1]
      }
      if (value === environment.COMMON_NAMSPACE) {
        this.commonPrefix = key.split(":")[1]
      }
    })
  }

  convertFromJSON(zahtevList: any) {
    const zahtevi: any[] = zahtevList.listaZahtevaAutorskoDelo[this.prefix + ':Zahtev_za_autorsko_delo'];
    if (Array.isArray(zahtevi)) {
      zahtevi.forEach((zahtev) => {
        let zahtevAutorskoDelo: ZahtevZaAutorskoDelo = this.fromXMLService.getAutoskoDeloFromXML(zahtev, this.prefix, this.commonPrefix);
        this.zahteviPAutorskoDelo.push(zahtevAutorskoDelo)
      })
    } else {
      let zahtevAutorskoDelo: ZahtevZaAutorskoDelo = this.fromXMLService.getAutoskoDeloFromXML(zahtevi, this.prefix, this.commonPrefix);
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

  getDataForUserTabel() {
    this.autorskoDeloService.getAllApproved().subscribe({
      next: (response) => {
        this.getAutorskaDelaFromResponse(response)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }
  
  getDataForSluzbenik(){
    this.autorskoDeloService.getAll().subscribe({
      next: (response) => {
        this.getAutorskaDelaFromResponse(response)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  openResenje(element: ZahtevZaAutorskoDelo) {
    this.dialog.open(FormResenjeComponent, {data: {id: element.id, type: typeZahteva.AUTORSKO_DELO}});
  }

  
  downloadRdf(id: string) {
    this.autorskoDeloService.downloadRdf(id).subscribe({
      next: (res: any) => {
        const filename: string =`zahtevZaAutorskoDelo${id}.rdf`;
        this.fileUtils.downloadDocument(res, filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }
  downloadJson(id: string) {
    this.autorskoDeloService.downloadJson(id).subscribe({
      next: (res: any) => {
        const filename: string =`zahtevZaAutorskoDelo${id}.json`;
        this.fileUtils.downloadDocument(res, filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }

  searchMetadata(query: string): void {
    if(query){
      this.autorskoDeloService.searchMetadata(query).subscribe({
        next: (res: any) => {
          this.getAutorskaDelaFromResponse(res);
        },
        error: (res: any) => {
          console.log(res)
        }
      })
    }  else{
      this.getDataByRole();
    }
  }

  openPatenDetailView(element: ZahtevZaAutorskoDelo){
    this.dialog.open(AutorskoDeloDetailViewComponent, {
      data: element,
    });
  }

  searchText(query: string): void {
    if(query){
      this.autorskoDeloService.searchText(query).subscribe({
        next: (res: any) => {
          this.getAutorskaDelaFromResponse(res);
        },
        error: (res: any) => {
          console.log(res)
        }
      })
    }  else{
      this.getDataByRole();
    }
  }

}
