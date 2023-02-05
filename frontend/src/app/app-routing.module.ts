import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FormAutorskoDeloComponent} from "./components/forms/form-autorsko-delo/form-autorsko-delo.component";
import {FormPatentComponent} from "./components/forms/form-patent/form-patent.component";
import {FormZigComponent} from "./components/forms/form-zig/form-zig.component";
import { HomePageEmployeeComponent } from './components/home-page-employee/home-page-employee.component';
import { HomePageUserComponent } from './components/home-page-user/home-page-user.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import {IzvestajComponent} from "./components/izvestaj/izvestaj.component";
import { PatentTabelViewComponent } from './components/tabel-view/patent-tabel-view/patent-tabel-view.component';
import { AutorskoDeloTableViewComponent } from './components/tabel-view/autorsko-delo-table-view/autorsko-delo-table-view.component';
import { ZigTableViewComponent } from './components/tabel-view/zig-table-view/zig-table-view.component';
import { ZigDetailViewComponent } from './components/detail-view/zig/zig-detail-view/zig-detail-view.component';
import { AutorskoDeloDetailViewComponent } from './components/detail-view/autorsko-delo/autorsko-delo-detail-view/autorsko-delo-detail-view.component';
import { PatentDetailViewComponent } from './components/detail-view/patent/patent-detail-view/patent-detail-view.component';


const routes: Routes = [
  {
    path: 'zahtev-za-autorsko-delo',
    component: FormAutorskoDeloComponent,
  },
  {
    path: 'zahtev-za-patent',
    component: FormPatentComponent,
  },
  {
    path: 'zahtev-za-zig',
    component: FormZigComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'home-page-employee',
    component: HomePageEmployeeComponent,
  },
  {
    path: 'home-page-user',
    component: HomePageUserComponent,
  },
  {
    path: 'registration',
    component: RegistrationComponent,
  },
  {
    path: 'izvestaj',
    component: IzvestajComponent,
  },
  {
    path: 'patents',
    component: PatentTabelViewComponent,
  },
  {
    path: 'autorska-dela',
    component: AutorskoDeloTableViewComponent,
  },
  {
    path: 'zigovi',
    component: ZigTableViewComponent,
  },
  {
    path: 'pregled-ziga/:id',
    component: ZigDetailViewComponent
  }  ,
  {
    path: 'pregled-autorsko-delo/:id',
    component: AutorskoDeloDetailViewComponent
  }
  ,
  {
    path: 'pregled-patent/:id',
    component: PatentDetailViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
