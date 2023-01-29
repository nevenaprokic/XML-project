import { Injectable } from '@angular/core';
import { Adresa, FizickoLice, KontaktPodaci, PravnoLice, Status } from 'src/app/model/common/common';
import { Podaci_o_dodatnoj_prijavi, Podaci_o_dostavljanju, PodnosilacZahteva, PrimalazZahteva, Pronalazac, Pronalazak, Punomcnik, RanijaPrijava, ZahtevZaPriznanjePatent, ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava } from 'src/app/model/patent/patent';

@Injectable({
  providedIn: 'root'
})
export class PatentFromXmlService {

  commonPrefix : string = ""
  constructor() { }

  getPatentFromXML(xml:any, prefix: string, commonPrefix: string) : ZahtevZaPriznanjePatent{
    console.log(xml)
    this.commonPrefix = commonPrefix;
    let attributes : any = xml["_attributes"]
    let brojPrijave = attributes.broj_prijave;
    let datumPodnosenja = attributes.datum_prijema_prijave;
    let priznatiDatumPodnosenja = attributes.priznati_datum_podnosenja;
    let dodatnaPrijava : Podaci_o_dodatnoj_prijavi = this.getPodaciODodatnojPrijavi(xml, prefix);
    let podatiODostavljanju : Podaci_o_dostavljanju = this.getPodaciODostavljanju(xml, prefix);
    let podnosilac : PodnosilacZahteva = this.getPodnosilac(xml, prefix);
    let pronalazac : Pronalazac = this.getPronalazac(xml, prefix);
    let primalac : PrimalazZahteva = this.getPrimalac(xml, prefix);
    let pronalazak : Pronalazak = this.getPronalazak(xml, prefix);
    let punomocnik : Punomcnik | undefined = this.getPunomocnik(xml, prefix);
    let prventsvo: ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined = this.getZaPrvenstvo(xml, prefix)
    let zahtev : ZahtevZaPriznanjePatent = new ZahtevZaPriznanjePatent(podnosilac, pronalazak, pronalazac, 
                                                                       podatiODostavljanju, punomocnik, dodatnaPrijava, prventsvo, brojPrijave, 
                                                                       datumPodnosenja, priznatiDatumPodnosenja,primalac)
    return zahtev;
  }

  getPodaciODodatnojPrijavi(xml:any, prefix: string) : Podaci_o_dodatnoj_prijavi{
    let podaciOPrvobitnojPrijavi = xml[prefix + ':Podaci_o_dodatnoj_prijavi']
    let dodatnaPrijava : Podaci_o_dodatnoj_prijavi = new Podaci_o_dodatnoj_prijavi(
      podaciOPrvobitnojPrijavi[prefix + ":Tip_prijave"]._text,
      podaciOPrvobitnojPrijavi[prefix + ":Broj_prvobitne_prijave"]._text,
      podaciOPrvobitnojPrijavi[prefix + ":Datum_podnosenja_prvobitne_prijave"]._text,
      )
    return dodatnaPrijava
  }

  getPodaciODostavljanju(xml:any,  prefix: string) : Podaci_o_dostavljanju{
    return new Podaci_o_dostavljanju( this.getAdresa(xml[prefix + ":Podaci_o_dostavljanju"]), xml[prefix + ":Podaci_o_dostavljanju"][prefix + ":Nacin_dostavljanja"]._text );
  }

  getAdresa(xml:any):Adresa{
     let adresa = xml[this.commonPrefix + ':Adresa']
     return new Adresa(adresa[this.commonPrefix + ':Broj']._text, adresa[this.commonPrefix + ':Ulica']._text, adresa[this.commonPrefix + ':Grad']._text, adresa[this.commonPrefix + ':Drzava']._text, adresa[this.commonPrefix + ':Postanski_broj']._text,)
  }

  getPodnosilac(xml:any, prefix: string) : PodnosilacZahteva{
    let ponosilac = xml[prefix +':Podnosilac_zahteva']
    let isPronalazac = ponosilac[prefix +':pronalazac']._text === "ture";
    let lice : FizickoLice | PravnoLice;
    if (ponosilac[prefix +':Lice']['_attributes']['xsi:type'].substring(4) === "TPravno_lice"){
      lice = this.getPravnoLice(ponosilac[prefix +':Lice'])
    }
    else{
      lice = this.getFizickoLice(ponosilac[prefix +':Lice']);
    }
    return new PodnosilacZahteva(lice, isPronalazac);

  }

  getPronalazak(xml:any, prefix: string) : Pronalazak{
     let pronalazak = xml[prefix +':Pronalazak']
     return new Pronalazak(pronalazak[prefix +':Naziv_na_srpskom']._text, pronalazak[prefix +':Naziv_na_engleskom']._text)
  }

  getPronalazac(xml:any, prefix: string) : Pronalazac{
    let pronalazac = xml[prefix +':Pronalazac'];
    let lice : FizickoLice = this.getFizickoLice(pronalazac);
    let isAnoniman: boolean = pronalazac['_attributes']['anoniman']  === "ture"
    if (lice.drzavljanstvo){
      return new Pronalazac(isAnoniman, lice.adresa, lice.kontaktPodaci, lice.ime, lice.prezime, lice.drzavljanstvo)
    }
    return new Pronalazac(isAnoniman, lice.adresa, lice.kontaktPodaci, lice.ime, lice.prezime)
  }
  // mockFizickoLice(): FizickoLice {
  //   let adresa : Adresa = new Adresa("Anon", "Anon", "Anon", "Anon", 'Anon')
  //   let kontakt : KontaktPodaci = new KontaktPodaci('anon', "anon@gmail.com", "anon")
  // }

  getPrimalac(xml:any, prefix: string):PrimalazZahteva{
    let primalac = xml[prefix +':Primalac_zahteva']
    let adresa :Adresa = this.getAdresa(primalac);
    let naziv: string = primalac[prefix +':Naziv']._text;
    return new PrimalazZahteva(adresa, naziv);
  }

  getPunomocnik(xml:any, prefix: string) : Punomcnik | undefined{
    let punomocnik = xml[prefix +':Punomocnik']
    if (punomocnik){
      let lice : FizickoLice | PravnoLice;
      let isPrijemPismena: boolean = punomocnik[prefix +':za_prijem_pismena']._text  === "ture"
      let isZaZastupanje: boolean = punomocnik[prefix +':za_zastupanje']._text  === "ture"
      if (punomocnik[prefix +':Lice']['_attributes']['xsi:type'].substring(4) === "TPravno_lice"){
        lice = this.getPravnoLice(punomocnik[prefix +':Lice'])
      }
      else{
        
        lice = this.getFizickoLice(punomocnik[prefix +':Lice']);
        console.log("bbb")
      }
      return new Punomcnik(lice, isZaZastupanje, isPrijemPismena);
    }
    return undefined
  }

  getZaPrvenstvo(xml:any, prefix: string): ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined{
    let prvenstvo = xml[prefix +':Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava']
    if(prvenstvo){
        let ranijePrijave: any[] = prvenstvo[prefix +':Ranija_prijava']
        let ranije_prijave : RanijaPrijava[] = [];
        if(Array.isArray(ranijePrijave)){
          ranijePrijave.forEach((prijava) => {
            ranije_prijave.push(this.getRanijaprijava(prijava, prefix))
          })
        }else{
          ranije_prijave.push(this.getRanijaprijava(ranijePrijave, prefix))
        }
        
        return new ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava(ranije_prijave)
    }
    return undefined;

  }

  getRanijaprijava(xml:any, prefix: string) : RanijaPrijava{
     let brojPrijave: string = xml[prefix +':Broj_prijave']._text
     let datumPrijema: string = xml[prefix +':Datum_podnosenja']._text
     let dvoslovnaOznakaDrzave: string = xml[prefix +':Dvoslovna_oznaka_drzave']._text
     return new RanijaPrijava(dvoslovnaOznakaDrzave, brojPrijave, datumPrijema);
  }

  getFizickoLice(xml:any) :FizickoLice{
    console.log(xml)
     let kontaktPodaci : KontaktPodaci = this.getKontaktPodaci(xml);
     let adresa: Adresa = this.getAdresa(xml);
     let ime: string =  xml[this.commonPrefix + ':Ime']._text;
     let prezime: string =  xml[this.commonPrefix + ':Prezime']._text;
     if (xml[this.commonPrefix + ':Drzavljanstvo']){
       return new FizickoLice(adresa, kontaktPodaci, ime, prezime, xml[this.commonPrefix + ':Drzavljanstvo']._text)
     }
     return new FizickoLice(adresa, kontaktPodaci, ime, prezime)
     
  }

  getPravnoLice(xml:any): PravnoLice{
    let kontaktPodaci : KontaktPodaci = this.getKontaktPodaci(xml);
    let adresa: Adresa = this.getAdresa(xml);
    let naziv: string = xml[this.commonPrefix + ':Naziv']._text;
    return new PravnoLice(adresa, kontaktPodaci, naziv);
  }

  getKontaktPodaci(xml: any) : KontaktPodaci{
    let kontakt = xml[this.commonPrefix + ':Kontakt_podaci']
     return new KontaktPodaci(kontakt[this.commonPrefix + ':Faks']._text, kontakt[this.commonPrefix + ':Email']._text, kontakt[this.commonPrefix + ':Telefon']._text)
  
  }
}
