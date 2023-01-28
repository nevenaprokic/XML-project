import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './services/user/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'frontend';

  ngOnInit(): void {
    
  }

  constructor(
    private userService: UserService,
  ) {
  }

  isUserLoggedIn(){
    return this.userService.isUserLoggedIn();
  }
}
