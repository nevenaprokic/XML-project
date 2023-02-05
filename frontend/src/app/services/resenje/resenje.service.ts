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
    return this.http.post(url + '/save-new', xml) as Observable<any>;
  }

  downnloadResenje(id: any, type: string): Observable<any> {
    const url = this.findUrl(type);
    return this.http.get(url + `/get-pdf/${id}`) as Observable<any>;
  }

  private findUrl(type: string) {
    let url = '';
    if (type === typeZahteva.PATENT) {
      url = this.IZVESTAJ_PATH_PATENT;
    } else if (type === typeZahteva.ZIG) {
      url = this.IZVESTAJ_PATH_ZIG;
    } else if (type === typeZahteva.AUTORSKO_DELO) {
      url = this.IZVESTAJ_PATH_AUTORSKO_DELO;
    }
    return url;
  }
}
