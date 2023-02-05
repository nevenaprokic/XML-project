import { X } from '@angular/cdk/keycodes';
import { Component, OnInit } from '@angular/core';
import { PatentService } from 'src/app/services/patent/patent.service';
import { XonomyService } from 'src/app/services/xonomy/xonomy.service';


declare const Xonomy: any;

@Component({
  selector: 'app-patent-form-xonomy',
  templateUrl: './patent-form-xonomy.component.html',
  styleUrls: ['./patent-form-xonomy.component.scss']
})
export class PatentFormXonomyComponent implements OnInit{

  ngOnInit(){
    
  }

 header = `xmlns="http://www.ftn.uns.ac.rs/p1"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:zaj="http://www.ftn.uns.ac.rs/zaj"`


  constructor (private xonomyService: XonomyService, private patentService: PatentService) {

  }

  ngAfterViewInit(){
    let element = document.getElementById("form");
    let specifikacija = this.xonomyService.zahtevZaPatentSpecifikacija;

    Xonomy.render(`<Zahtev_za_priznanje_patenta ${this.header}></Zahtev_za_priznanje_patenta>`, element, specifikacija) //onjekat je z a pravila renderovanja
  }

  send(){
    // let xml : string = `<?xml version="1.0" encoding="UTF-8"?><Zahtev_za_priznanje_patenta ${this.header}><Pronalazak><Naziv_na_srpskom xml:space='preserve'>dsada</Naziv_na_srpskom><Naziv_na_engleskom xml:space='preserve'>asdadx</Naziv_na_engleskom></Pronalazak> <Pronalazac anoniman="false"><Adresa> <Drzava xml:space='preserve'>dddd</Drzava><Grad xml:space='preserve'>dddddd</Grad><Ulica xml:space='preserve'>qqqq</Ulica><Broj xml:space='preserve'>11</Broj><Postanski_broj xml:space='preserve'>21000</Postanski_broj></Adresa><Kontakt_podaci><Telefon xml:space='preserve'>067/123-1233</Telefon><Faks xml:space='preserve'>021/000222</Faks><Email xml:space='preserve'>user@gmail.com</Email></Kontakt_podaci><Ime xml:space='preserve'>natasa</Ime><Prezime xml:space='preserve'>lakovic</Prezime></Pronalazac><Podnosilac_zahteva  ><Lice><Adresa><Drzava xml:space='preserve'>aaaa</Drzava><Grad xml:space='preserve'>sdsda</Grad><Ulica xml:space='preserve'>sadaq</Ulica><Broj xml:space='preserve'>11</Broj><Postanski_broj xml:space='preserve'>21000</Postanski_broj></Adresa><Kontakt_podaci><Telefon xml:space='preserve'>067/123-1233</Telefon><Faks xml:space='preserve'>021/000222</Faks><Email xml:space='preserve'>n@gmail.com</Email></Kontakt_podaci><Naziv xml:space='preserve'>Ustanova1</Naziv></Lice><pronalazac xml:space='preserve'>false</pronalazac></Podnosilac_zahteva><Podaci_o_dostavljanju><Adresa><Drzava xml:space='preserve'>dsds</Drzava><Grad xml:space='preserve'>gfdgf</Grad><Ulica xml:space='preserve'>hgf</Ulica><Broj xml:space='preserve'>1</Broj><Postanski_broj xml:space='preserve'>21000</Postanski_broj></Adresa><Nacin_dostavljanja xml:space='preserve'>elektronski</Nacin_dostavljanja></Podaci_o_dostavljanju></Zahtev_za_priznanje_patenta>`
   let xml = Xonomy.harvest();
    console.log(xml)
    xml =xml.replaceAll(" xml:space='preserve'", "")

    xml = xml.trim()
    // console.log(a)
    

    xml = xml.replaceAll('Adresa>', 'zaj:Adresa>')
   
    xml =xml.replaceAll('Kontakt_podaci>', 'zaj:Kontakt_podaci>')

    xml =xml.replaceAll('Ulica>', 'zaj:Ulica>')
    xml =xml.replaceAll('<Ulica', '<zaj:Ulica')
    xml =xml.replaceAll('Grad>', 'zaj:Grad>')
    xml =xml.replaceAll('<Grad', '<zaj:Grad')
    xml =xml.replaceAll('Drzava>', 'zaj:Drzava>')
    xml =xml.replaceAll('<Drzava', '<zaj:Drzava')
    xml =xml.replaceAll('Broj>', 'zaj:Broj>')
    xml =xml.replaceAll('<Broj', '<zaj:Broj')
    xml =xml.replaceAll('Postanski_broj>', 'zaj:Postanski_broj>')
    xml =xml.replaceAll('<Postanski_broj', '<zaj:Postanski_broj')

    xml =xml.replaceAll('Ime>', 'zaj:Ime>')
    xml =xml.replaceAll('<Ime', '<zaj:Ime')
    xml =xml.replaceAll('Prezime>', 'zaj:Prezime>')
    xml =xml.replaceAll('<Prezime', '<zaj:Prezime')
    xml =xml.replaceAll('Naziv>', 'zaj:Naziv>')
    xml =xml.replaceAll('<Naziv', '<zaj:Naziv')
    xml =xml.replaceAll('<zaj:Naziv_na_srpskom', '<Naziv_na_srpskom')
    xml =xml.replaceAll('<zaj:Naziv_na_engleskom', '<Naziv_na_engleskom')
    xml =xml.replaceAll('Email>', 'zaj:Email>')
    xml =xml.replaceAll('<Email', '<zaj:Email')
    xml =xml.replaceAll('Faks>', 'zaj:Faks>')
    xml =xml.replaceAll('<Faks', '<zaj:Faks')
    xml =xml.replaceAll('Telefon>', 'zaj:Telefon>')
    xml =xml.replaceAll('<Telefon', '<zaj:Telefon')

    xml = xml.replaceAll('<Pronalazak>', `<Primalac_zahteva>
    <zaj:Adresa>
      <zaj:Drzava>Republika Srbija</zaj:Drzava>
      <zaj:Grad>Beograd</zaj:Grad>
      <zaj:Ulica>Kneginje Ljubice</zaj:Ulica>
      <zaj:Broj>2</zaj:Broj>
      <zaj:Postanski_broj>11000</zaj:Postanski_broj>
    </zaj:Adresa>
    <Naziv>Zavod za intelektualnu svojinu</Naziv>
  </Primalac_zahteva><Pronalazak>`)

    console.log(xml)

    let finalXml: string = ''

    let tokens = xml.split('Lice')
    for(let i in tokens){
      let token = tokens[i]
      console.log(token.trim().substring(1))
        if (token.substring(1).startsWith('<zaj:Adresa')){
          if (token.includes('Ime')){
            let sub = token.substring(1)
            let str = 'Lice ' + `xsi:type="zaj:TFizicko_lice">` + sub  + 'Lice'
            finalXml+= str
          }
          else{
            let sub = token.substring(1)
            let str = 'Lice ' + `xsi:type="zaj:TPravno_lice">` + sub + 'Lice'
            finalXml+= str
          }
        }
        else{
          finalXml+= token
        }
    }
    console.log(finalXml)

    this.patentService.saveNew(finalXml).subscribe({
      next: response => console.log(response)
    });





  }

}
