import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {

  private IZVESTAJ_PATH = environment.ZIG_BASE_URL + '/izvestaj';
  // private IZVESTAJ_PATH = environment.PATENT_BASE_URL + '/izvestaj';
  constructor(private http: HttpRequestService) { }

  getIzvestaj(xml: any) {
    const url = this.IZVESTAJ_PATH + `/get`;
    return this.http.post(url, xml);
  }
}
