import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user/user';

@Injectable({
  providedIn: 'root'
})
export class UserToXmlService {

  constructor() { }

  convert = require('xml-js');

  convertusertoXML(form : any){
    // return `<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    //   <user xmlns="http://ftn.uns.ac.rs/user">
    //     <name>${user.firstName}</name>
    //     <last_name>${user.firstName}</last_name>
    //     <email>${user.lastName}</email>
    //     <password>${password}</password>
    //     <role>${user.role}</role>
    //   </user>`
    // "name",
    // "lastName",
    // "email",
    // "password",
    // "role",
    // "jwt"
    const newUser: any = {};
    newUser.email = form.email;
    newUser.password = form.password;
    newUser.lastName = form.lastName;
    newUser.name = form.firstName
    newUser.role = "KORISNIK"

    const signInUser = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      user: { _attributes:
          { xmlns: 'http://ftn.uns.ac.rs/user',
            'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance' },
            name: { _text: '' }, 
            last_name: { _text: '' }, 
            email: { _text: '' }, 
            password: { _text: '' },
            role: { _text: '' },  } };

    signInUser.user.email = newUser.email;
    signInUser.user.password = newUser.password;
    signInUser.user.last_name = newUser.lastName;
    signInUser.user.name = newUser.name;
    signInUser.user.role = newUser.role;
    console.log(signInUser)
    
    const loginUserXML = this.convert.js2xml(signInUser, {compact: true, ignoreComment: true, spaces: 4});
    return loginUserXML;
  }
}
