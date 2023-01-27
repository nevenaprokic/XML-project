import { Component } from '@angular/core';
import {Router} from "@angular/router";
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

  constructor(private router: Router, private userService: UserService ) { }

  colapseMenu(){
    const menuContainer = document.getElementById('menuDiv') as HTMLInputElement;
    menuContainer.style.display = "none";
    const openMenuButton = document.getElementById('collapsedMenu') as HTMLInputElement;
    openMenuButton.style.display = "flex";
  }

  openMenu(){
    const menuContainer = document.getElementById('menuDiv') as HTMLInputElement;
    menuContainer.style.display = "flex";
    const openMenuButton = document.getElementById('collapsedMenu') as HTMLInputElement;
    openMenuButton.style.display = "none";
  }

  openFormAutorskoDelo() {
    this.router.navigateByUrl("zahtev-za-autorsko-delo");
  }

  openFormPatent(){
    this.router.navigateByUrl("zahtev-za-patent") 
  }

  openFormZig(){
    this.router.navigateByUrl("zahtev-za-zig");
  }

  patentOverview(){
    this.router.navigateByUrl("patents");
  }
}
