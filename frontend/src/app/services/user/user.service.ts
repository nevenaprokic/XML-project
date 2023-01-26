import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { environment } from 'src/app/environments/environment';
import { Role } from 'src/app/model/user/role';
import { HttpClientService } from '../custom-http/http-client.service';
import { UserFromXmlService } from './converter/from-xml/user-from-xml.service';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }


  isUserLoggedIn(): boolean {
    return this.getCurrentUserToken() !== null;
  }

  setCurrentUser(userData : {email: string, token: string, role: string}){
    sessionStorage.setItem('currentUser', JSON.stringify({email: userData.email, token: userData.token, role: userData.role}));
  }

  getCurrentUserToken() : string{
    const currentUser = sessionStorage.getItem('currentUser');
    return currentUser ? JSON.parse(currentUser).token : null;

  }

  getCurrentUserEmail() : string{
    const currentUser = sessionStorage.getItem('currentUser');
    return currentUser ? JSON.parse(currentUser).email : null;
  }

  getRoleCurrentUserRole() : string{
    const currentUser = sessionStorage.getItem('currentUser');
    return currentUser ? JSON.parse(currentUser).role : null;
  }

  login(user:any){
    return this.http.post(environment.USER_BASE_URL + "/auth/login", user, {headers: this.headers, responseType: 'text'});
  }

  registeruser(userXML: string){
    return this.http.post(environment.USER_BASE_URL + "/user/add", userXML, {headers: this.headers, responseType: 'text'})
  }

}
