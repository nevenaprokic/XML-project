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

  searchMetadata(request: string, status: string) {
    const url = this.AUTORSKO_DELO_PATH + `/searchMetadata?status=${status}&request=${request}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  searchText(request: string, status: string){
    const url = this.AUTORSKO_DELO_PATH + `/searchText?status=${status}&txt=${request}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  downloadRdf(documentId: string) {
    const url = this.AUTORSKO_DELO_PATH + `/get-rdf/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  downloadJson(documentId: string) {
    const url = this.AUTORSKO_DELO_PATH + `/get-json/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  downloadPdf(documentId: string) {
    const url = this.AUTORSKO_DELO_PATH + `/get-pdf/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  downloadXHTML(documentId: string) {
    const url = this.AUTORSKO_DELO_PATH + `/get-xhtml/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }

  getAllApproved(){
    const url = this.AUTORSKO_DELO_PATH + `/find-all-approved`;
    return this.httpRequestService.get(url)
  }

  getPrilog(documentId: string, prilog: string){
    const url = this.AUTORSKO_DELO_PATH + `/get-prilog?documentId=${documentId}&imageName=${prilog}`;
    return this.httpRequestService.get(url)
  }

  getById(documentId: string) : Observable<any>{
    documentId = documentId.replace('/', '_');
    const url = this.AUTORSKO_DELO_PATH + `/${documentId}`;
    return this.httpRequestService.get(url) as Observable<any>;
  }
}
