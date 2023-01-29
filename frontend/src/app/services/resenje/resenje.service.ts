import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";
import {Observable} from "rxjs";
import {typeZahteva} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class ResenjeService {

  private IZVESTAJ_PATH_ZIG = environment.ZIG_BASE_URL + '/resenje';
  private IZVESTAJ_PATH_PATENT = environment.PATENT_BASE_URL + '/resenje';
  private IZVESTAJ_PATH_AUTORSKO_DELO = environment.AUTORSKO_DELO_BASE_URL + '/resenje';

  constructor(private http: HttpRequestService) {
  }

  sendResenje(xml: any, type: string): Observable<any> {
    const url = this.findUrl(type);
    return this.http.post(url, xml) as Observable<any>;
  }

  private findUrl(type: string) {
    let url = '';
    if (type === typeZahteva.PATENT) {
      url = this.IZVESTAJ_PATH_PATENT + `/save-new`;
    } else if (type === typeZahteva.ZIG) {
      url = this.IZVESTAJ_PATH_ZIG + `/save-new`;
    } else if (type === typeZahteva.AUTORSKO_DELO) {
      url = this.IZVESTAJ_PATH_AUTORSKO_DELO + `/save-new`;
    }
    return url;
  }
}
