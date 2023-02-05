import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";
import {typeZahteva} from "../../model/model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {

  private IZVESTAJ_PATH_ZIG = environment.ZIG_BASE_URL + '/izvestaj';
  private IZVESTAJ_PATH_PATENT = environment.PATENT_BASE_URL + '/izvestaj';
  private IZVESTAJ_PATH_AUTORSKO_DELO = environment.AUTORSKO_DELO_BASE_URL + '/izvestaj';

  constructor(private http: HttpRequestService) {
  }

  getIzvestaj(xml: any,  typeIzvestaja: string) {
    const url = this.findUrl(typeIzvestaja, "/get");
    return this.http.post(url, xml);
  }

  getPDF(izvestaj: any, typeIzvestaja: string) {
    const url = this.findUrl(typeIzvestaja, "/getPDF");
    return this.http.post(url, izvestaj) as Observable<any>;
  }

  private findUrl(type: string, sufix:string) {
    let url = '';
    if (type === "zig") {
      url = this.IZVESTAJ_PATH_ZIG + sufix;
    } else if (type === "patent") {
      url = this.IZVESTAJ_PATH_PATENT + sufix;
    } else if (type === "autorsko delo") {
      url = this.IZVESTAJ_PATH_AUTORSKO_DELO + sufix;
    }
    return url;
  }
}
