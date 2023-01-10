import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu/menu.component';
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import { FormAutorskoDeloComponent } from './components/forms/form-autorsko-delo/form-autorsko-delo.component';
import { FormPatentComponent } from './components/forms/form-patent/form-patent.component';
import { FormZigComponent } from './components/forms/form-zig/form-zig.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatRadioModule} from "@angular/material/radio";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import { FormKontaktPodaciComponent } from './components/forms/form-items/zajednicko/form-kontakt-podaci/form-kontakt-podaci.component';
import { FormPravnoLiceComponent } from './components/forms/form-items/zajednicko/form-pravno-lice/form-pravno-lice.component';
import { FormFizickoLiceComponent } from './components/forms/form-items/zajednicko/form-fizicko-lice/form-fizicko-lice.component';
import { FormAdresaComponent } from './components/forms/form-items/zajednicko/form-adresa/form-adresa.component';
import { FormZnakComponent } from './components/forms/form-items/zaZig/form-znak/form-znak.component';
import { FormTakseComponent } from './components/forms/form-items/zaZig/form-takse/form-takse.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    FormAutorskoDeloComponent,
    FormPatentComponent,
    FormZigComponent,
    FormKontaktPodaciComponent,
    FormPravnoLiceComponent,
    FormFizickoLiceComponent,
    FormAdresaComponent,
    FormZnakComponent,
    FormTakseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatListModule,
    MatButtonModule,
    MatFormFieldModule,
    MatRadioModule,
    MatInputModule,
    FormsModule,
    MatIconModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
