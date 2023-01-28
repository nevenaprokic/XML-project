import {Component} from '@angular/core';
import {Router} from "@angular/router";
import { Role } from 'src/app/model/user/role';
import { UserService } from 'src/app/services/user/user.service';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

    isSluzbenik : boolean = false;

  constructor(private router: Router, private userService: UserService ) {
    this.isSluzbenik = this.userService.getRoleCurrentUserRole() === Role.SLUZBENIK
   }

    colapseMenu() {
        const menuContainer = document.getElementById('menuDiv') as HTMLInputElement;
        menuContainer.style.display = "none";
        const openMenuButton = document.getElementById('collapsedMenu') as HTMLInputElement;
        openMenuButton.style.display = "flex";
    }

    openMenu() {
        const menuContainer = document.getElementById('menuDiv') as HTMLInputElement;
        menuContainer.style.display = "flex";
        const openMenuButton = document.getElementById('collapsedMenu') as HTMLInputElement;
        openMenuButton.style.display = "none";
    }

    openFormAutorskoDelo() {
        this.router.navigateByUrl("zahtev-za-autorsko-delo");
    }

    openFormPatent() {
        this.router.navigateByUrl("zahtev-za-patent");
    }

    openFormZig() {
        this.router.navigateByUrl("zahtev-za-zig");
    }

    openIzvestaji() {
        this.router.navigateByUrl("izvestaj");
    }

    patentOverview(){
      this.router.navigateByUrl("patents");
    }

    autorskoDeloOverview(){
      this.router.navigateByUrl('autorska-dela')
    }

}
