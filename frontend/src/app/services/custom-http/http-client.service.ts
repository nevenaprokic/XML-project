import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private http: HttpClient, private userService: UserService) {
  }

  public createHeader() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization': "Bearer " + this.userService.getCurrentUserToken()
    });
    return headers;
  }

  get(url: any) {
    return this.http.get(url, {
      headers: this.createHeader(),
      responseType: 'text'
    });
  }

  delete(url: any) {
    return this.http.delete(url, {
      headers: this.createHeader(),
    });
  }

  post(url:any, data:any) {
    return this.http.post(url, data, {
      headers: this.createHeader()
    });
  }

  postT<Type>(url:any, data:any) {
    return this.http.post<Type>(url, data, {
      headers: this.createHeader(),
    });
  }

  getT<Type>(url:any) {
    return this.http.get<Type>(url, {
      headers: this.createHeader()
    });
  }

  getWithText(url:any) {
    return this.http.get(url, {
      headers: this.createHeader(),
      responseType: 'text'
    });
  }

  postWithText(url:any, data:any) {
    return this.http.post(url, data, {
      headers: this.createHeader(),
      responseType: 'text'
    });
  }

  deleteWithText(url:any) {
    return this.http.delete(url, {
      headers: this.createHeader(),
      responseType: 'text'
    });
  }

  put(url:any, data:any) {
    return this.http.put(url, data, {
      headers: this.createHeader()
    });
  }

  putT<Type>(url:any, data:any) {
    return this.http.put<Type>(url, data, {
      headers: this.createHeader(),
    });
  }

  deleteT<Type>(url:any) {
    return this.http.delete<Type>(url, {
      headers: this.createHeader(),
    });
  }
}
