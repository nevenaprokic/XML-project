import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {Sort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {environment} from 'src/app/environments/environment';
import {ZahtevZaPriznanjeZiga} from 'src/app/model/zig';
import {UserService} from 'src/app/services/user/user.service';
import {Toastr} from 'src/app/services/utils/toastr/toastr.service';
import {ZigXmlConverterService} from 'src/app/services/zig/zig-xml-converter/zig-xml-converter.service';
import {ZigService} from 'src/app/services/zig/zig.service';
import {FormResenjeComponent} from "../../forms/form-resenje/form-resenje.component";
import {typeZahteva} from "../../../model/model";
import {MatDialog} from "@angular/material/dialog";
import { FileUtilService } from 'src/app/services/utils/file-util/file-util.service';
import { ZigDetailViewComponent } from '../../detail-view/zig/zig-detail-view/zig-detail-view.component';
import { Status } from 'src/app/model/common/common';
import {  Router } from '@angular/router';
import { ResenjeService } from 'src/app/services/resenje/resenje.service';

@Component({
  selector: 'app-zig-table-view',
  templateUrl: './zig-table-view.component.html',
  styleUrls: ['./zig-table-view.component.scss']
})
export class ZigTableViewComponent implements OnInit{

  zahteviZig: ZahtevZaPriznanjeZiga[] = [];
  isLoaded: boolean = false;
  basicColoumns = ["vrsta po korisniku", "vrsta po izgledu", "podnosilac", "datum podnošenja", "prikaz"]
  displayedColumns: string[] = []
  gettingDataFinished: boolean = false;
  dataSource!: MatTableDataSource<ZahtevZaPriznanjeZiga>;
  isSluzbenik: boolean = false;
  prefix: string = '';
  commonPrefix : string = '';
  isEmptySource : boolean = false;

  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatTable) matTable!: MatTable<any>;

  metadataOptions = [    
    'ukupna_taksa', 
    'vrsta', 
    'podnosilac_prijave',
    'datum_podnosenja_prijave',
    'prilog'
  ]

  constructor(private zigService: ZigService,
              private userService: UserService,
              private zigFromXML: ZigXmlConverterService,
              private toastr: Toastr,
              private dialog: MatDialog,
              private fileUtils: FileUtilService,
              private resenjeService: ResenjeService,
              private router: Router){

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  ngOnInit(): void {
    this.getDataByRole();
  }

  getDataByRole() {
    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK"){
      this.isSluzbenik = true;
      this.displayedColumns = [...this.basicColoumns, "rešenje",  "pdf", "html", "rdf", "json"]
      this.getDataForSluzbenik()
    }
    else{
      this.displayedColumns = ['broj',...this.basicColoumns]
      this.getDataForUserTabel();
    }
  }

  getDataForUserTabel(){
    this.zigService.getAllApproved().subscribe({
      next: (response) => {
        this.getFromResponse(response);
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  getDataForSluzbenik(){
    this.zigService.getAll().subscribe({
      next: (response) => {
        this.getFromResponse(response);
      },
      error: (error) => {
        console.log(error)
      }
    })
  }
  getFromResponse(response: any) {
    this.zahteviZig = []
    const convert = require('xml-js');
    const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4, encodeURI: "utf-8"}));
    if (zahtevList.listaZahtevaZiga && Object.keys(zahtevList.listaZahtevaZiga).length > 1){
      const atrributes = zahtevList.listaZahtevaZiga._attributes;
      this.getPrefix(atrributes)
      this.convertFromJSON(zahtevList)
      this.isEmptySource = false;
    } else{
      this.isEmptySource = true;
      this.gettingDataFinished = true;
      this.toastr.info("Nema odgovarajućih dokumenata")
    }

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
    const zahtevi : any[] = zahtevList.listaZahtevaZiga[this.prefix + ':Zahtev_za_priznanje_ziga'];
    if(Array.isArray(zahtevi)){
      zahtevi.forEach((zahtev) => {
        let zahtevZaPriznanjeZiga : ZahtevZaPriznanjeZiga = this.zigFromXML.getZigFromXML(zahtev, this.prefix, this.commonPrefix);
        this.zahteviZig.push(zahtevZaPriznanjeZiga)
      })
    }else{
      let zahtevZaPriznanjeZiga : ZahtevZaPriznanjeZiga = this.zigFromXML.getZigFromXML(zahtevi, this.prefix, this.commonPrefix);
      this.zahteviZig.push(zahtevZaPriznanjeZiga)
    }

    this.gettingDataFinished = true;
    this.setDataSource(this.zahteviZig)
  }

  openResenje(element: ZahtevZaPriznanjeZiga) {
    if(!element.idResenja || element.status === Status.NEOBRADJEN){
      let dialogRef = this.dialog.open(
        FormResenjeComponent, 
        {  
          height: '600px',
          width: '1000px',
          data: {id: element.id, type: typeZahteva.ZIG}
        },
        );
      dialogRef.afterClosed().subscribe(res => {
        console.log(res.data)
        element.idResenja = res.data
        element.status = Status.SVI
      })
    }
    else{
      this.downloadResenje(element.idResenja)
    }
  }

  downloadResenje(idResenja: string) {
    this.resenjeService.downnloadResenje(idResenja, typeZahteva.ZIG).subscribe({
      next: (res: any) => {
        const filename = `resenje-${idResenja}`
        this.fileUtils.downloadDocumentFromBase64(res, 'pdf', filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }

  downloadRdf(id: string) {
    this.zigService.downloadRdf(id).subscribe({
      next: (res: any) => {
        const filename: string =`zahtevZaPriznanjeZiga${id}.rdf`;
        this.fileUtils.downloadDocument(res, filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }
  downloadJson(id: string) {
    this.zigService.downloadJson(id).subscribe({
      next: (res: any) => {
        const filename: string =`zahtevZaPriznanjeZiga${id}.json`;
        this.fileUtils.downloadDocument(res, filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }

  searchMetadata(query: string): void {
    if(query){
      const status = this.userService.getRoleCurrentUserRole() === "SLUZBENIK" ?  Status.SVI : Status.ODOBREN;
      this.zigService.searchMetadata(query, status).subscribe({
        next: (res: any) => {
          this.getFromResponse(res);
        },
        error: (res: any) => {
          console.log(res)
        }
      })
    } else{
      this.getDataByRole()
    }
  }

  searchText(query: string): void {
    if(query){
      const status = this.userService.getRoleCurrentUserRole() === "SLUZBENIK" ?  Status.SVI : Status.ODOBREN;
      this.zigService.searchText(query, status).subscribe({
        next: (res: any) => {
          this.getFromResponse(res);
        },
        error: (res: any) => {
          console.log(res)
        }
      })
    }  else{
      this.getDataByRole();
    }
  }

  downloadPdf(id: string) {
    this.zigService.downloadPdf(id).subscribe({
      next: (res: any) => {
        const filename = `ZahtevZaZig${id}`
        this.fileUtils.downloadDocumentFromBase64(res, 'pdf', filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }

  downloadXHTML(id: string) {
    this.zigService.downloadXHTML(id).subscribe({
      next: (res: any) => {
        const filename = `ZahtevZaZig${id}`
        this.fileUtils.downloadDocumentFromBase64(res, 'html', filename)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }
}
