import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpRequestService} from "../utils/http-request/http-request.service";
import {Observable} from "rxjs";

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
}
