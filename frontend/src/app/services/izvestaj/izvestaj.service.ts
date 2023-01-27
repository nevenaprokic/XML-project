import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {

  private IZVESTAJ_PATH = environment.ZIG_BASE_URL + '/izvestaj'
  constructor(private httpRequestService: HttpRequestService) { }

  getIzvestaj(xml: any): Observable<any> {
    const url = this.IZVESTAJ_PATH + `/get`;
    return this.httpRequestService.post(url, xml) as Observable<any>;
  }
}
