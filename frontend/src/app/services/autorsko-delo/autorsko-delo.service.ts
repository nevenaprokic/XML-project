import { Injectable } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';
import { HttpRequestService } from '../utils/http-request/http-request.service';

@Injectable({
  providedIn: 'root'
})
export class AutorskoDeloService {

  private AUTORSKO_DELO_PATH = environment.AUTORSKO_DELO_BASE_URL + '/autorsko-delo'
  constructor(private httpRequestService: HttpRequestService) { }

  saveNew(xml: any): Observable<any> {
    const url = this.AUTORSKO_DELO_PATH + `/save-new`;
    return this.httpRequestService.post(url, xml) as Observable<any>;
  }

  getAll(){
    const url = this.AUTORSKO_DELO_PATH + `/findAll`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  searchMetadata(request: string) {
    const url = this.AUTORSKO_DELO_PATH + `/searchMetadata?request=${request}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

}
