import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {

  
  constructor(private httpClient: HttpClient, private userService: UserService) {}

  createHeaders(): HttpHeaders {
      const headers = new HttpHeaders({
          'Content-type': 'application/xml',
          'Response-type': 'application/xml',
          'Authorization': "Bearer " + this.userService.getCurrentUserToken()
      });

      return headers;
  }

  post(url: string, body: any) : Observable<any> {
      const headers = this.createHeaders();
      return this.httpClient.post(url, body, {headers, responseType: 'text'}) 
  }

  get(url: string) : Observable<any> {
      const headers = this.createHeaders();
      return this.httpClient.get(url, {headers, responseType: 'text'}) 
  }

  patch(url: string, body: any) : Observable<any> {
      const headers = this.createHeaders();
      return this.httpClient.patch(url, body, {headers, responseType: 'text'}) 
  }

  put(url: string, body: any) : Observable<any> {
      const headers = this.createHeaders();
      return this.httpClient.put(url, body, {headers, responseType: 'text'}) 
  }

  delete(url: string): Observable<any> {
      const headers = this.createHeaders();
      return this.httpClient.delete(url, {headers, responseType: 'text'}) 
  }
}
