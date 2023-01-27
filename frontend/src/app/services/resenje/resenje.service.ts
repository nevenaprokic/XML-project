import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ResenjeService {

  private IZVESTAJ_PATH = environment.ZIG_BASE_URL + '/resenje';
  constructor(private httpRequestService: HttpRequestService) { }

  sendResenje(xml: any): Observable<any> {
    const url = this.IZVESTAJ_PATH + `/save-new`;
    return this.httpRequestService.post(url, xml) as Observable<any>;
  }
}
