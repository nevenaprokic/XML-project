import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FormAutorskoDeloComponent} from "./components/forms/form-autorsko-delo/form-autorsko-delo.component";
import {FormPatentComponent} from "./components/forms/form-patent/form-patent.component";
import {FormZigComponent} from "./components/forms/form-zig/form-zig.component";
import { LoginComponent } from './login/login.component';

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
    path: '**',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
