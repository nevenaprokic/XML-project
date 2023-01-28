import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MenuComponent} from './components/menu/menu.component';
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {FormAutorskoDeloComponent} from './components/forms/form-autorsko-delo/form-autorsko-delo.component';
import {FormPatentComponent} from './components/forms/form-patent/form-patent.component';
import {FormZigComponent} from './components/forms/form-zig/form-zig.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatRadioModule} from "@angular/material/radio";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {
  FormKontaktPodaciComponent
} from './components/forms/form-items/zajednicko/form-kontakt-podaci/form-kontakt-podaci.component';
import {
  FormPravnoLiceComponent
} from './components/forms/form-items/zajednicko/form-pravno-lice/form-pravno-lice.component';
import {
  FormFizickoLiceComponent
} from './components/forms/form-items/zajednicko/form-fizicko-lice/form-fizicko-lice.component';
import {FormAdresaComponent} from './components/forms/form-items/zajednicko/form-adresa/form-adresa.component';
import {FormZnakComponent} from './components/forms/form-items/zaZig/form-znak/form-znak.component';
import {FormTakseComponent} from './components/forms/form-items/zaZig/form-takse/form-takse.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {
  FormDopunskaPrijavaComponent
} from './components/forms/form-items/zaPatent/form-dopunska-prijava/form-dopunska-prijava.component';
import {
  FormPronalazakComponent
} from './components/forms/form-items/zaPatent/form-pronalazak/form-pronalazak.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormTabelaComponent} from './components/forms/form-items/zaPatent/form-tabela/form-tabela.component';
import {
  FormIzborLicaComponent
} from './components/forms/form-items/zajednicko/form-izbor-lica/form-izbor-lica.component';
import {FormAutorComponent} from "./components/forms/form-items/zaAutorskoDelo/form-autor/form-autor.component";
import {HttpClientModule} from '@angular/common/http';
import {ToastrModule} from 'ngx-toastr';
import {HomePageEmployeeComponent} from './components/home-page-employee/home-page-employee.component';
import {HomePageUserComponent} from './components/home-page-user/home-page-user.component';
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ErrorMessageComponent} from './components/error-message/error-message.component';
import {FormResenjeComponent} from './components/forms/form-resenje/form-resenje.component';
import {MAT_DIALOG_DATA, MatDialogModule} from "@angular/material/dialog";
import {IzvestajComponent} from './components/izvestaj/izvestaj.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from '@angular/material/core';
import { PatentTabelViewComponent } from './components/patent-tabel-view/patent-tabel-view.component';
import {MatTableModule} from "@angular/material/table"
import { MatSortModule } from '@angular/material/sort'
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';

const MaterialModule = [
  BrowserModule,
  AppRoutingModule,
  MatListModule,
  MatButtonModule,
  MatFormFieldModule,
  MatRadioModule,
  MatInputModule,
  FormsModule,
  MatIconModule,
  ReactiveFormsModule,
  MatCheckboxModule,
  BrowserAnimationsModule,
  HttpClientModule,
  ToastrModule.forRoot(),
  MatDialogModule,
  MatSnackBarModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSortModule,
  MatTableModule,
  MatPaginatorModule,
  MatProgressBarModule
]

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
        FormTakseComponent,
        FormDopunskaPrijavaComponent,
        FormPronalazakComponent,
        FormTabelaComponent,
        FormIzborLicaComponent,
        FormAutorComponent,
        LoginComponent,
        HomePageEmployeeComponent,
        HomePageUserComponent,
        RegistrationComponent,
        ErrorMessageComponent,
        PatentTabelViewComponent,
        FormResenjeComponent,
        IzvestajComponent
    ],
  imports: [
    ...MaterialModule
  ],
  exports: [MaterialModule],
  providers: [{provide: MAT_DIALOG_DATA, useValue: {}}],
  bootstrap: [AppComponent]

})
export class AppModule {
}
