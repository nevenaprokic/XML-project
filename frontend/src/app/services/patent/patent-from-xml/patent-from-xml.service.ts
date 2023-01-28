import { Injectable } from '@angular/core';
import { Adresa, FizickoLice, KontaktPodaci, PravnoLice } from 'src/app/model/common/common';
import { Podaci_o_dodatnoj_prijavi, Podaci_o_dostavljanju, PodnosilacZahteva, PrimalazZahteva, Pronalazac, Pronalazak, Punomcnik, RanijaPrijava, ZahtevZaPriznanjePatent, ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava } from 'src/app/model/patent/patent';

@Injectable({
  providedIn: 'root'
})
export class PatentFromXmlService {

  constructor() { }

  getPatentFromXML(xml:any) : ZahtevZaPriznanjePatent{
    let attributes : any = xml["_attributes"]
    let brojPrijave = attributes.broj_prijave;
    let datumPodnosenja = attributes.datum_prijema_prijave;
    let priznatiDatumPodnosenja = attributes.priznati_datum_podnosenja;
    let dodatnaPrijava : Podaci_o_dodatnoj_prijavi = this.getPodaciODodatnojPrijavi(xml);
    let podatiODostavljanju : Podaci_o_dostavljanju = this.getPodaciODostavljanju(xml);
    let podnosilac : PodnosilacZahteva = this.getPodnosilac(xml);
    let pronalazac : Pronalazac = this.getPronalazac(xml);
    let primalac : PrimalazZahteva = this.getPrimalac(xml);
    let pronalazak : Pronalazak = this.getPronalazak(xml);
    let punomocnik : Punomcnik | undefined = this.getPunomocnik(xml);
    let prventsvo: ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined = this.getZaPrvenstvo(xml)
    let zahtev : ZahtevZaPriznanjePatent = new ZahtevZaPriznanjePatent(primalac, podnosilac, pronalazak, pronalazac, 
                                                                       podatiODostavljanju,  punomocnik, dodatnaPrijava, prventsvo, brojPrijave, 
                                                                       datumPodnosenja, priznatiDatumPodnosenja)
    return zahtev;
  }

  getPodaciODodatnojPrijavi(xml:any) : Podaci_o_dodatnoj_prijavi{
    let podaciOPrvobitnojPrijavi = xml['ns5:Podaci_o_dodatnoj_prijavi']
    let dodatnaPrijava : Podaci_o_dodatnoj_prijavi = new Podaci_o_dodatnoj_prijavi(
      podaciOPrvobitnojPrijavi["ns5:Tip_prijave"]._text,
      podaciOPrvobitnojPrijavi["ns5:Broj_prvobitne_prijave"]._text,
      podaciOPrvobitnojPrijavi["ns5:Datum_podnosenja_prvobitne_prijave"]._text,
      )
    return dodatnaPrijava
  }

  getPodaciODostavljanju(xml:any) : Podaci_o_dostavljanju{
    return new Podaci_o_dostavljanju( this.getAdresa(xml["ns5:Podaci_o_dostavljanju"]), xml["ns5:Podaci_o_dostavljanju"]["ns5:Nacin_dostavljanja"]._text );
  }

  getAdresa(xml:any):Adresa{
     let adresa = xml['ns4:Adresa']
     return new Adresa(adresa['ns4:Broj']._text, adresa['ns4:Ulica']._text, adresa['ns4:Grad']._text, adresa['ns4:Drzava']._text, adresa['ns4:Postanski_broj']._text,)
  }

  getPodnosilac(xml:any) : PodnosilacZahteva{
    let ponosilac = xml['ns5:Podnosilac_zahteva']
    let isPronalazac = ponosilac['ns5:pronalazac']._text;
    let lice : FizickoLice | PravnoLice;
    console.log(ponosilac['ns5:Lice']['_attributes']['xsi:type'])
    if (ponosilac['ns5:Lice']['_attributes']['xsi:type'] === "ns4:TPravno_lice"){
      lice = this.getPravnoLice(ponosilac['ns5:Lice'])
    }
    else{
      lice = this.getFizickoLice(ponosilac['ns5:Lice']);
    }
    return new PodnosilacZahteva(lice, isPronalazac);

  }

  getPronalazak(xml:any) : Pronalazak{
     let pronalazak = xml['ns5:Pronalazak']
     return new Pronalazak(pronalazak['ns5:Naziv_na_srpskom']._text, pronalazak['ns5:Naziv_na_engleskom']._text)
  }

  getPronalazac(xml:any) : Pronalazac{
    let pronalazac = xml['ns5:Pronalazac'];
    let lice : FizickoLice = this.getFizickoLice(pronalazac);
    let isAnoniman: boolean = pronalazac['_attributes']['anoniman']
    if (lice.drzavljanstvo){
      return new Pronalazac(isAnoniman, lice.adresa, lice.kontaktPodaci, lice.ime, lice.prezime, lice.drzavljanstvo)
    }
    return new Pronalazac(isAnoniman, lice.adresa, lice.kontaktPodaci, lice.ime, lice.prezime)
  }
  // mockFizickoLice(): FizickoLice {
  //   let adresa : Adresa = new Adresa("Anon", "Anon", "Anon", "Anon", 'Anon')
  //   let kontakt : KontaktPodaci = new KontaktPodaci('anon', "anon@gmail.com", "anon")
  // }

  getPrimalac(xml:any):PrimalazZahteva{
    let primalac = xml['ns5:Primalac_zahteva']
    let adresa :Adresa = this.getAdresa(primalac);
    let naziv: string = primalac['ns5:Naziv']._text;
    return new PrimalazZahteva(adresa, naziv);
  }

  getPunomocnik(xml:any) : Punomcnik | undefined{
    let punomocnik = xml['ns5:Punomocnik']
    if (punomocnik){
      let lice : FizickoLice | PravnoLice;
      let isPrijemPismena: boolean = punomocnik['ns5:za_prijem_pismena']._text
      let isZaZastupanje: boolean = punomocnik['ns5:za_zastupanje']._text
      if (punomocnik['ns5:Lice']['_attributes']['xsi:type'] === "ns4:TPravno_lice"){
        lice = this.getPravnoLice(punomocnik['ns5:Lice'])
      }
      else{
        
        lice = this.getFizickoLice(punomocnik['ns5:Lice']);
        console.log("bbb")
      }
      return new Punomcnik(lice, isZaZastupanje, isPrijemPismena);
    }
    return undefined
  }

  getZaPrvenstvo(xml:any): ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined{
    let prvenstvo = xml['ns5:Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava']
    if(prvenstvo){
        let ranijePrijave: any[] = prvenstvo['ns5:Ranija_prijava']
        let ranije_prijave : RanijaPrijava[] = [];
        ranijePrijave.forEach((prijava) => {
          ranije_prijave.push(this.getRanijaprijava(prijava))
        })
    }
    return undefined;

  }

  getRanijaprijava(xml:any) : RanijaPrijava{
     let brojPrijave: string = xml['ns5:Broj_prijave']._text
     let datumPrijema: Date = xml['ns5:Datum_podnosenja']._text
     let dvoslovnaOznakaDrzave: string = xml['ns5:Dvoslovna_oznaka_drzave']._text
     return new RanijaPrijava(dvoslovnaOznakaDrzave, brojPrijave, datumPrijema);
  }

  getFizickoLice(xml:any) :FizickoLice{
     let kontaktPodaci : KontaktPodaci = this.getKontaktPodaci(xml);
     let adresa: Adresa = this.getAdresa(xml);
     let ime: string =  xml['ns4:Ime']._text;
     let prezime: string =  xml['ns4:Prezime']._text;
     if (xml['ns4:Drzavljanstvo']){
       return new FizickoLice(adresa, kontaktPodaci, ime, prezime, xml['ns4:Drzavljanstvo']._text)
     }
     return new FizickoLice(adresa, kontaktPodaci, ime, prezime)
     
  }

  getPravnoLice(xml:any): PravnoLice{
    let kontaktPodaci : KontaktPodaci = this.getKontaktPodaci(xml);
    let adresa: Adresa = this.getAdresa(xml);
    let naziv: string = xml['ns4:Naziv']._text;
    return new PravnoLice(adresa, kontaktPodaci, naziv);
  }

  getKontaktPodaci(xml: any) : KontaktPodaci{
    let kontakt = xml['ns4:Kontakt_podaci']
     return new KontaktPodaci(kontakt['ns4:Faks']._text, kontakt['ns4:Email']._text, kontakt['ns4:Telefon']._text)
  
  }
}
