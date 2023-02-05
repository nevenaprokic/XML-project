import { P } from '@angular/cdk/keycodes';
import { Injectable } from '@angular/core';


declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public zahtevZaPatentSpecifikacija = {
    elements: {
      Zahtev_za_priznanje_patenta: {
        menu:[{
          caption: 'Dodaj pronalazak',
          action: Xonomy.newElementChild,
          actionParameter: '<Pronalazak></Pronalazak>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Pronalazak")
          }
        },
        {
          caption: 'Dodaj pronalazaca',
          action: Xonomy.newElementChild,
          actionParameter: '<Pronalazac></Pronalazac>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Pronalazac")
          }
        },
        {
          caption: 'Dodaj podnosioca prijave',
          action: Xonomy.newElementChild,
          actionParameter: '<Podnosilac_zahteva></Podnosilac_zahteva>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Podnosilac_zahteva")
          }
        }, 
        {
          caption: 'Dodaj punomocnika',
          action: Xonomy.newElementChild,
          actionParameter: '<Punomocnik></Punomocnik>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Punomocnik")
          }
        },
        {
          caption: 'Dodaj podatke o dostavljanju',
          action: Xonomy.newElementChild,
          actionParameter: '<Podaci_o_dostavljanju></Podaci_o_dostavljanju>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Podaci_o_dostavljanju")
          }
        }
        ,
        {
          caption: 'Dodaj ranije  prijave za priznanje prava prvenstva',
          action: Xonomy.newElementChild,
          actionParameter: '<Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava></Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava")
          }
        }
        ,
        {
          caption: 'Dodaj raniju prijavu',
          action: Xonomy.newElementChild,
          actionParameter: '<Podaci_o_dodatnoj_prijavi></Podaci_o_dodatnoj_prijavi>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Podaci_o_dodatnoj_prijavi")
          }
        }],
        attributes: {

        }
      },
      Pronalazak: this.getPronalazak(),
      Pronalazac: this.getPronalazac(),
      Podnosilac_zahteva: this.getPodnosilac(),
      Punomocnik: this.getPunomocnik(),
      Podaci_o_dostavljanju: this.getPodaciODostavljanju(),
      Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava: this.getPrvenstvo(),
      Podaci_o_dodatnoj_prijavi: this.getPodaciODodatnojprijavi(),

      Naziv_na_srpskom: {
        mustBeBefore: ['Naziv_na_engleskom'],
        hasText: true,
        oneliner:true,
      },
      Naziv_na_engleskom: {
        hasText: true,
        oneliner:true,
      },
      "Ime": {
        mustBeBefore: ['Prezime'],
        hasText: true,
        oneliner:true,
      },
      "Prezime": {
        oneliner:true,
        hasText: true
      },
      "Ulica": {
        mustBeBefore: ['Broj', 'Postanski_broj'],
        hasText: true,
        oneliner:true,
      },
      "Broj": {
        mustBeBefore: ['Postanski_broj'],
        hasText: true,
        oneliner:true,
      }
      ,
      "Grad": {
        mustBeBefore: ['Ulica', 'Broj', 'Postanski_broj'],
        hasText: true,
        oneliner:true,
      },
      "Drzava": {
        mustBeBefore: ['Grad', 'Ulica', 'Broj', 'Postanski_broj'],
        hasText: true,
        oneliner:true,
      },
      "Postanski_broj": {
      
        hasText: true,
        oneliner:true,
      },
      "Email": {
        hasText: true,
        oneliner:true,
      },
      "Telefon": {
        mustBeBefore: ['Email', 'Faks'],
        hasText: true,
        oneliner:true,
      },
      "Faks": {
        mustBeBefore: ['Email'],
        hasText: true,
        oneliner:true,
      },
      "Adresa" : this.getAdressa(),
      "Kontakt_podaci": this.getkontakt(),
      Lice: this.returnLice(),
      pronalazac: {
        hasText: true,
        onliner:true,
        asker: Xonomy.askPicklist,
        askerParameter: ["true", "false"]
      },
      Naziv:{
        hasText: true,
        oneliner:true
      },
      za_prijem_pismena :{
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ["true", "false"]
      }
      , za_zastupanje :{
        hasText: true,
        mustBeBefore: ['za_prijem_pismena'],
        asker: Xonomy.askPicklist,
        askerParameter: ["true", "false"]
      },
      Nacin_dostavljanja: {
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ["elekstronski", "papirna_forma"]
      },
      Tip_prijave: {
        mustBeBefore: ['Datum_podnosenja_prvobitne_prijave'],
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: ["dopunska", "izdvojena"]
      },
      Broj_prijave: {
        mustBeBefore: ['Dvoslovna_oznaka_drzave'],
        hasText: true,
      },
      Datum_podnosenja: {
        mustBeBefore: ['Dvoslovna_oznaka_drzave', 'Broj_prvobitne_prijave'],
        hasText: true,
      },
      Dvoslovna_oznaka_drzave: {
        hasText: true,
      },      
      Broj_prvobitne_prijave: {
        hasText: true
      },
      ranija_prijava: this.getRanijaPrijava(),

    }
  }

  getPronalazak() : any{
    return {
      mustBeBefore: ['Pronalazac', 'Podnosilac_zahteva', 'Punomocnik', 'Podaci_o_dostavljanju', 'Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava', 'Podaci_o_dodatnoj_prijavi'],
      menu:[{
        caption: 'Dodaj naziv na srpskom',
        action: Xonomy.newElementChild,
        actionParameter: '<Naziv_na_srpskom></Naziv_na_srpskom>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Naziv_na_srpskom")
        }
      },
      {
        caption: 'Dodaj naziv na engleskom',
        action: Xonomy.newElementChild,
        actionParameter: '<Naziv_na_engleskom></Naziv_na_engleskom>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Naziv_na_engleskom")
        }
      }],
    }
  }

  getPronalazac() : any{
    return {
        mustBeBefore: ['Podnosilac_zahteva', 'Punomocnik', 'Podaci_o_dostavljanju', 'Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava', 'Podaci_o_dodatnoj_prijavi'],
        menu:[{
          caption: 'Dodaj adresu',
          action: Xonomy.newElementChild,
          actionParameter: '<Adresa></Adresa>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Adresa")
          }
        },
        {
          caption: 'Dodaj kontakt podatke',
          action: Xonomy.newElementChild,
          actionParameter: '<Kontakt_podaci></Kontakt_podaci>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Kontakt_podaci")
          }
        }, 
        {
          caption: 'Dodaj ime',
          action: Xonomy.newElementChild,
          actionParameter: '<Ime></Ime>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Ime")
          }
        },
        {
          caption: 'Dodaj prezime',
          action: Xonomy.newElementChild,
          actionParameter: '<Prezime></Prezime>',
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("Prezime")
          }
        },
        {
          caption: 'Da li je anoniman',
          action: Xonomy.newAttribute,
          actionParameter: {name: 'anoniman', value: 'false'},
          hideIf: function(jsElement:any){
            return jsElement.hasChildElement("pronalazac")
          }
        }],
    
    }
  }

  getFizickoLice() : any{
    return {
      
    }
  }

  getAdressa(){
    return {
      mustBeBefore: ['za_prijem_pismena', 'za_zastupanje','Kontakt_podaci',' Ime', 'Prezime','Nacin_dostavljanja'],
      menu:[
      {
        caption: 'Dodaj Drzavu',
        action: Xonomy.newElementChild,
        actionParameter: '<Drzava></Drzava>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Drzava")
        }
      },{
        caption: 'Dodaj grad',
        action: Xonomy.newElementChild,
        actionParameter: '<Grad></Grad>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Grad")
        }
      },{
        caption: 'Dodaj ulicu',
        action: Xonomy.newElementChild,
        actionParameter: '<Ulica></Ulica>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Ulica")
        }
      },
      {
        caption: 'Dodaj broj',
        action: Xonomy.newElementChild,
        actionParameter: '<Broj></Broj>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Broj")
        }
      },
      {
        caption: 'Dodaj postancki broj',
        action: Xonomy.newElementChild,
        actionParameter: '<Postanski_broj></Postanski_broj>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Postanski_broj")
        }
      }]
    }
  }

  getkontakt(){
    
   return  {
      mustBeBefore: [' Ime', 'Prezime'],
      menu:[
    {
      caption: 'Dodaj telefon',
      action: Xonomy.newElementChild,
      actionParameter: '<Telefon></Telefon>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Telefon")
      }
    }
    ,
    {
      caption: 'Dodaj falks',
      action: Xonomy.newElementChild,
      actionParameter: '<Faks></Faks>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Faks")
      }
    },
    {
      caption: 'Dodaj email',
      action: Xonomy.newElementChild,
      actionParameter: '<Email></Email>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Email")
      }
    }]
  }
} 

getPodnosilac(): any{
  return {
    mustBeBefore: ['Punomocnik', 'Podaci_o_dostavljanju', 'Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava', 'Podaci_o_dodatnoj_prijavi'],
    menu:[{
    caption: 'Dodaj lice',
    action: Xonomy.newElementChild,
    actionParameter: '<Lice></Lice>',
    hideIf: function(jsElement:any){
      return jsElement.hasChildElement("Lice")
    }
  },
  {
    caption: 'Da li je pronalazac',
    action: Xonomy.newElementChild,
    actionParameter: '<pronalazac></pronalazac>',
    hideIf: function(jsElement:any){
      return jsElement.hasChildElement("pronalazac")
    }
  }]
  }
}

getPunomocnik(): any{
  return {
    mustBeBefore: ['Podaci_o_dostavljanju', 'Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava', 'Podaci_o_dodatnoj_prijavi'],
    menu:[{
    caption: 'Dodaj lice',
    action: Xonomy.newElementChild,
    actionParameter: '<Lice></Lice>',
    hideIf: function(jsElement:any){
      return jsElement.hasChildElement("Lice")
    }
  },
  {
    caption: 'Da li je zastupanje',
    action: Xonomy.newElementChild,
    actionParameter: '<za_zastupanje></za_zastupanje>',
    hideIf: function(jsElement:any){
      return jsElement.hasChildElement("za_zastupanje")
    }
  },
  {
    caption: 'Da li je za prijem pismena',
    action: Xonomy.newElementChild,
    actionParameter: '<za_prijem_pismena></za_prijem_pismena>',
    hideIf: function(jsElement:any){
      return jsElement.hasChildElement("za_prijem_pismena")
    }
  }]
  }
}


returnLice(){
  return {
    mustBeBefore: ['Podnosilac_zahteva', 'Punomocnik', 'Podaci_o_dostavljanju', 'Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava', 'Podaci_o_dodatnoj_prijavi'],
    menu:[{
      caption: 'Dodaj adresu',
      action: Xonomy.newElementChild,
      actionParameter: '<Adresa></Adresa>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Adresa")
      }
    },
    {
      caption: 'Dodaj kontakt podatke',
      action: Xonomy.newElementChild,
      actionParameter: '<Kontakt_podaci></Kontakt_podaci>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Kontakt_podaci")
      }
    }, 
    {
      caption: 'Dodaj Ime (za fizicko lice)',
      action: Xonomy.newElementChild,
      actionParameter: '<Ime></Ime>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Naziv")|| jsElement.hasChildElement("Ime")
      }
    },
    {
      caption: 'Dodaj prezime (za fizicko lice)',
      action: Xonomy.newElementChild,
      actionParameter: '<Prezime></Prezime>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Naziv")|| jsElement.hasChildElement("Prezime")
      }
    }
    , 
    {
      caption: 'Dodaj Naziv (za pravno lice)',
      action: Xonomy.newElementChild,
      actionParameter: '<Naziv></Naziv>',
      hideIf: function(jsElement:any){
        return jsElement.hasChildElement("Ime") || jsElement.hasChildElement("Prezime")|| jsElement.hasChildElement("Naziv")
      }
    },],
  }
}

  getPodaciODostavljanju(){
    return {
      mustBeBefore: ['Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava', 'Podaci_o_dodatnoj_prijavi'],
      menu:[{
        caption: 'Dodaj adresu',
        action: Xonomy.newElementChild,
        actionParameter: '<Adresa></Adresa>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Adresa")
        }
      },
      {
        caption: 'Dodaj nacin dostavljanja',
        action: Xonomy.newElementChild,
        actionParameter: '<Nacin_dostavljanja>elektronski</Nacin_dostavljanja>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Nacin_dostavljanja")
        }
      },]
    }
  }

  getPrvenstvo(){
    return{
      mustBeBefore: ['Podaci_o_dodatnoj_prijavi'],
      menu:[{
        caption: 'Dodaj raniju prijavu',
        action: Xonomy.newElementChild,
        actionParameter: '<Ranija_prijava></Ranija_prijava>',
        }]
    }
  }

  getRanijaPrijava(){
    return{
      menu:[{
        caption: 'Dodaj datum podnosenja',
        action: Xonomy.newElementChild,
        actionParameter: '<Datum_podnosenja></Datum_podnosenja>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Datum_podnosenja")
        }
      },
      {
        caption: 'Dodaj broj prijave',
        action: Xonomy.newElementChild,
        actionParameter: '<Broj_prijave></Broj_prijave>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Broj_prijave")
        }
      },
      {
        caption: 'Dodaj dvoslovnu oznaku drzave',
        action: Xonomy.newElementChild,
        actionParameter: '<Dvoslovna_oznaka_drzave></Dvoslovna_oznaka_drzave>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Dvoslovna_oznaka_drzave")
        }
      }]
    }
  }

  getPodaciODodatnojprijavi(){
    return {
      menu:[{
        caption: 'Dodaj tip prijave',
        action: Xonomy.newElementChild,
        actionParameter: '<Tip_prijave></Tip_prijave>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Tip_prijave")
        }
      },
      {
        caption: 'Dodaj datum podnosenja ranije prijave',
        action: Xonomy.newElementChild,
        actionParameter: '<Datum_podnosenja_prvobitne_prijave></Datum_podnosenja_prvobitne_prijave>',
        hideIf: function(jsElement:any){
          return jsElement.hasChildElement("Datum_podnosenja_prvobitne_prijave")
        }
      }]
    }
  }
}

//onliner:true
// asker: Xsonomy.askPicklist,
// askParameter: ["AA", "BB"]