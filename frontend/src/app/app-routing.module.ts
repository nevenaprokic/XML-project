import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FormAutorskoDeloComponent} from "./components/forms/form-autorsko-delo/form-autorsko-delo.component";
import {FormPatentComponent} from "./components/forms/form-patent/form-patent.component";
import {FormZigComponent} from "./components/forms/form-zig/form-zig.component";
import { HomePageEmployeeComponent } from './components/home-page-employee/home-page-employee.component';
import { HomePageUserComponent } from './components/home-page-user/home-page-user.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
