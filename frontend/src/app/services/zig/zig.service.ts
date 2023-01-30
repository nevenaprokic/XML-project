import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";
import {Observable} from "rxjs";
import { ZahtevZaPriznanjeZiga } from 'src/app/model/zig';
import { Sort } from '@angular/material/sort';

@Injectable({
  providedIn: 'root'
})
export class ZigService {


  private ZIG_PATH = environment.ZIG_BASE_URL + '/zig'
  constructor(private httpRequestService: HttpRequestService) { }

  saveNew(xml: any): Observable<any> {
    const url = this.ZIG_PATH + `/save-new`;
    return this.httpRequestService.post(url, xml) as Observable<any>;
  }

  getAll(){
    const url = this.ZIG_PATH + `/findAll`;
    return this.httpRequestService.get(url)
  }

  getAllApproved(){
    const url = this.ZIG_PATH + `/find-all-approved`;
    return this.httpRequestService.get(url)
  }

  sortData(sort: Sort, dataSource: ZahtevZaPriznanjeZiga[]): ZahtevZaPriznanjeZiga[] {
    if (!sort.active || sort.direction === '') {
      return dataSource
    }

    let sortedData = dataSource.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'vrsta po korisniku':
          return this.compare(a.zig.vrstaNaOsnovuKorisnika, b.zig.vrstaNaOsnovuKorisnika, isAsc);
        case 'vrsta po izgledu':
          return this.compare(a.zig.vrstaNaOsnovuIzgleda, b.zig.vrstaNaOsnovuIzgleda, isAsc);
        case 'podnosilac':
          if (a.podnosiociStr && b.podnosiociStr){
            return this.compare(a.podnosiociStr, b.podnosiociStr, isAsc);
          }
          return 0;
        case 'datum podnošenja':
          if (a.datumPodnosenjaZahteva && b.datumPodnosenjaZahteva){
            return this.compare(a.datumPodnosenjaZahteva, b.datumPodnosenjaZahteva, isAsc);
          }
          return 0;
        default:
          return 0;
      }
    });
    return sortedData;
  }
  compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  downloadRdf(documentId: string) {
    const url = this.ZIG_PATH + `/get-rdf/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  downloadJson(documentId: string) {
    const url = this.ZIG_PATH + `/get-json/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }
}
