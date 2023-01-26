import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../../services/user/user.service';
import { User } from '../../model/user/user';
import { UserFromXmlService } from '../../services/user/converter/from-xml/user-from-xml.service';
import { Role } from '../../model/user/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  hide : boolean = true;
  errorMessage : boolean = false;
  isLoggedin?: boolean = undefined;

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.email]),
    password: new FormControl(''),
  });

  ngOnInit(): void {
  }

  constructor(private router: Router, private matDialog: MatDialog, private userService: UserService, private userFromXML : UserFromXmlService) {}

  onSubmit() {
    console.log(this.loginForm.value)
    const convert = require('xml-js');
    let loginUserXML = this.convertUserXML(convert);
      
    this.userService.login(loginUserXML).subscribe({
      next: (response) => {
        console.log(response); //konvertovati xml u objekat
        const user : User = this.userFromXML.getUserFromXML(response);
        const token : string = this.userFromXML.getTokenFromXML(response);
        this.userService.setCurrentUser({email : user.email, token: token, role: user.role});
        this.routeToHomePage(user.role);
      },
      error: (error) => {
        
    }
    })
  }

  convertUserXML(convert:any){
    const auth: any = {};
    auth.username = this.loginForm.value.email;
    auth.password = this.loginForm.value.password;
    const signInUser = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      token: { _attributes:
          { xmlns: 'http://ftn.uns.ac.rs/login',
            'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance' },
            username: { _text: '' }, password: { _text: '' } } };
    signInUser.token.username = auth.username;
    signInUser.token.password = auth.password;
    console.log(signInUser)
    
    const loginUserXML = convert.js2xml(signInUser, {compact: true, ignoreComment: true, spaces: 4});
    return loginUserXML;
  }

  routeToHomePage(userRole : string){
    userRole === Role.KORISNIK.toString()?  this.router.navigateByUrl("/home-page-user") : this.router.navigateByUrl("/home-page-employee")
  }

  onRegister(){
    this.router.navigateByUrl("/registration")
  }
}
