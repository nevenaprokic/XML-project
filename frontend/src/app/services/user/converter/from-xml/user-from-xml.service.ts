import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user/user';

@Injectable({
  providedIn: 'root'
})
export class UserFromXmlService {

  constructor() { }

  getUserFromXML(xml:any): User{
    const convert = require('xml-js');
    const userToken = JSON.parse(convert.xml2json(xml, {compact: true, spaces: 4}));
    const user = userToken.user;
    return new User(user.email._text, user.name._text, user.last_name._text, user.role._text);
  }

  getTokenFromXML(xml:any) : string{
    const convert = require('xml-js');
    const userToken = JSON.parse(convert.xml2json(xml, {compact: true, spaces: 4}));
    return userToken.user.jwt.__text;
  }
}
