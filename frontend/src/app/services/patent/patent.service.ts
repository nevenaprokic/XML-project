import { Injectable } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { HttpClientService } from '../custom-http/http-client.service';
import { HttpRequestService } from '../utils/http-request/http-request.service';

@Injectable({
  providedIn: 'root'
})
export class PatentService {

  private PATENt_PATH = environment.PATENT_BASE_URL + '/patent'

  constructor(private http: HttpRequestService) { }

  getAll(){
      return this.http.get(environment.PATENT_BASE_URL + "/patent/all-patents") //da li radi
  }

  saveNew(xml: any): Observable<any> {
    const url = this.PATENt_PATH + `/save-new`;
    return this.http.post(url, xml) as Observable<any>;
  }

  sortData(sort: Sort, dataSource: ZahtevZaPriznanjePatent[]): ZahtevZaPriznanjePatent[] {
    if (!sort.active || sort.direction === '') {
      return dataSource
    }

    let sortedData = dataSource.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'podnosilac':
          return this.compare(a.podnosilac.lice.kontaktPodaci.email, b.podnosilac.lice.kontaktPodaci.email, isAsc);
        case 'pronalazak':
          return this.compare(a.pronalazak.nazivNaSrpskom, b.pronalazak.nazivNaSrpskom, isAsc);
        case 'datum podnošenja':
          if (a.datumPodnosenja && b.datumPodnosenja){
            return this.compare(a.datumPodnosenja, b.datumPodnosenja, isAsc);
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
}
