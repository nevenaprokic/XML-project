import { Component, OnInit } from '@angular/core';
import { ZahtevZaPriznanjePatent } from 'src/app/model/patent/patent';
import { PatentFromXmlService } from 'src/app/services/patent/patent-from-xml/patent-from-xml.service';
import { PatentService } from 'src/app/services/patent/patent.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-patent-tabel-view',
  templateUrl: './patent-tabel-view.component.html',
  styleUrls: ['./patent-tabel-view.component.scss']
})
export class PatentTabelViewComponent implements OnInit{

  zahteviPatent: ZahtevZaPriznanjePatent[] = [];
  isLoaded: boolean = false;

  constructor(private patentService: PatentService, private userService: UserService, private patentFromXML: PatentFromXmlService){

  }

  ngOnInit(): void {
    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK"){
      this.patentService.getAll().subscribe({
        next: (response) => {
            const convert = require('xml-js');
            const zahtevList : any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
            const zahtevi : any[] = zahtevList.patentList['ns5:Zahtev_za_priznanje_patenta'];
            zahtevi.forEach((zahtev) => {
              let zahtevZaPriznanjePatent : ZahtevZaPriznanjePatent = this.patentFromXML.getPatentFromXML(zahtev);
              console.log(zahtevZaPriznanjePatent)
              this.zahteviPatent.push(zahtevZaPriznanjePatent)
            })
        },
        error: (error) => {
          console.log(error)
        }
      })
    }
  }

}
