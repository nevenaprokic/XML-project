
import {Injectable} from '@angular/core';
import {Sort} from '@angular/material/sort';
import {environment} from 'src/app/environments/environment';
import {ZahtevZaPriznanjePatent} from 'src/app/model/patent/patent';
import {HttpClientService} from '../custom-http/http-client.service';
import {HttpRequestService} from '../utils/http-request/http-request.service';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PatentService {

  private PATENT_PATH = environment.PATENT_BASE_URL + '/patent'

  constructor(private http: HttpRequestService) { }

  getAll(){
      return this.http.get(this.PATENT_PATH + "/all-patents") //da li radi
  }

  saveNew(xml: any): Observable<any> {
    const url = this.PATENT_PATH + `/save-new`;
    return this.http.post(url, xml) as Observable<any>;
  }

  getById(documentId: string) : Observable<any>{
    documentId = documentId.replace('/', '_');
    const url = this.PATENT_PATH + `/${documentId}`;
    return this.http.get(url) as Observable<any>;
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
          if (a.datumPodnosenja && b.datumPodnosenja) {
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

  downloadRdf(documentId: string) {
    const url = this.PATENT_PATH + `/get-rdf/${documentId}`;
    return this.http.get(url) as Observable<any>;
  }

  downloadJson(documentId: string) {
    const url = this.PATENT_PATH + `/get-json/${documentId}`;
    return this.http.get(url) as Observable<any>;
  }
}
