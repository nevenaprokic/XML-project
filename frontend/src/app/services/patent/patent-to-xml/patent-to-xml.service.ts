import { Injectable } from '@angular/core';
import { Adresa, FizickoLice, KontaktPodaci, PravnoLice } from 'src/app/model/common/common';
import { Podaci_o_dodatnoj_prijavi, Podaci_o_dostavljanju, PodnosilacZahteva, Pronalazac, Pronalazak, Punomcnik, RanijaPrijava, ZahtevZaPriznanjePatent, ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava } from 'src/app/model/patent/patent';


@Injectable({
  providedIn: 'root'
})
export class PatentToXmlService {

  constructor() { }

  converFormToObject(form: any) : ZahtevZaPriznanjePatent {
    let adresaDostavljanja : Podaci_o_dostavljanju = new Podaci_o_dostavljanju(form.adresaDostavljanja, form.patentFormParent.nacinDostavljanja)
    let podnosilac : PodnosilacZahteva = this.getPodnosilacPrijave(form)
    let pronalazak : Pronalazak = new Pronalazak(form.pronalazak.pronalazakNaSrpskom,form.pronalazak.pronalazakNaEngleskom)
    let pronalazac : Pronalazac = this.getPronalazac(form, podnosilac)
    let punomocnik : Punomcnik | undefined = this.getPunomocnik(form)
    let prvenstvo : ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined = this.getPrvenstvo(form); 
    let dodatnaPrijava: Podaci_o_dodatnoj_prijavi | undefined = this.getPodaciODodatnojprijavi(form)

    return new ZahtevZaPriznanjePatent(podnosilac, pronalazak, pronalazac, adresaDostavljanja, punomocnik,  dodatnaPrijava, prvenstvo);
  }


  getPrvenstvo(form: any): ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined {
    if (form.zahtevZaPrvenstvo.prijave.length > 0){
      let ranijePrijave : RanijaPrijava[] = []
        let prijave : any[] = form.zahtevZaPrvenstvo.prijave
        prijave.forEach((p) => {
            ranijePrijave.push(new RanijaPrijava(p.DvoslovnaOznakaDrzave, p.brojprijave, p.datumPodnosenjaPrijave))
        })
        return new ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava(ranijePrijave);
    } 
    return undefined
  }

  getPodaciODodatnojprijavi(form: any) : Podaci_o_dodatnoj_prijavi | undefined{
    if (form.patentFormParent.vrstaPrijave){
      return new Podaci_o_dodatnoj_prijavi(form.patentFormParent.vrstaPrijave, form.dopunskaPrijava.brojPrvobitnePrijave, form.dopunskaPrijava.datumPodnosenja)
    }
    return undefined
  }

  getPunomocnik(form: any): Punomcnik | undefined {    
    if (form.punomocnik.lice.lice !== ""){
      let lice : FizickoLice | PravnoLice;
      let zaZastupanje : boolean = form.patentFormParent.podnosilacJePunomocnik === "punomoćnik za zastupanje"
      let zaPrijemaPismena : boolean = form.patentFormParent.podnosilacJePunomocnik === "punomoćnik za prijem pismena"
      let kontakPodaci : KontaktPodaci = new KontaktPodaci(form.punomocnikKontaktPodaci.faks, form.punomocnikKontaktPodaci.email, form.punomocnikKontaktPodaci.telefon)
      let adresa : Adresa = new Adresa(form.punomocnikAdresa.broj, form.punomocnikAdresa.ulica, form.punomocnikAdresa.grad, form.punomocnikAdresa.drzava, form.punomocnikAdresa.postanskiBroj)
      if (form.punomocnik.lice === "TFizicko_lice"){
        let lice = new FizickoLice(adresa, kontakPodaci, form.punomocnik.punomocnikFizickoLice.ime, form.punomocnik.punomocnikFizickoLice.prezime, form.punomocnik.punomocnikFizickoLice.drzavljanstvo)       
        return new Punomcnik(lice, zaZastupanje, zaPrijemaPismena)
      }
      else{
        let lice =  new PravnoLice(adresa, kontakPodaci, form.punomocnik.punomocnikPravnoLice.nazivPravnoLice)
        return new Punomcnik(lice, zaZastupanje, zaPrijemaPismena)
      }      
    }
    return undefined 
  }

  getPronalazac(form: any, podnosilac : PodnosilacZahteva): Pronalazac {
    let anoniman : boolean = form.patentFormParent.pronalazacNijeNaveden
    if (!form.patentFormParent.podnosilacJePronalazac){
      if(!anoniman) {
        let kontakPodaci : KontaktPodaci = new KontaktPodaci(form.pronalazacKontaktPodaci.faks, form.pronalazacKontaktPodaci.email, form.pronalazacKontaktPodaci.telefon)
        let adresa : Adresa = new Adresa(form.pronalazacAdresa.broj, form.pronalazacAdresa.ulica, form.pronalazacAdresa.grad, form.pronalazacAdresa.drzava, form.pronalazacAdresa.postanskiBroj)
        // let lice : FizickoLice = new FizickoLice(adresa, kontakPodaci, form.pronalazacFizickoLice.ime, form.pronalazacFizickoLice.prezime, form.pronalazacFizickoLice.drzavljanstvo)
        return new Pronalazac(anoniman, adresa, kontakPodaci, form.pronalazacFizickoLice.ime, form.pronalazacFizickoLice.prezime, form.pronalazacFizickoLice.drzavljanstvo)
      }
      else{
        let kontakPodaci : KontaktPodaci = new KontaktPodaci("000/00000", "anon#gmail.com", "060/000-000")
        let adresa : Adresa = new Adresa("0", "anon", "anon", "anon", "11000")
        // let lice : FizickoLice = new FizickoLice(adresa, kontakPodaci, form.pronalazacFizickoLice.ime, form.pronalazacFizickoLice.prezime, form.pronalazacFizickoLice.drzavljanstvo)
        return new Pronalazac(anoniman, adresa, kontakPodaci, "anon", "anon", "anon")
      }
    }
    else{
      let pronalazacLice : FizickoLice = podnosilac.lice as FizickoLice;
      return new Pronalazac(anoniman, pronalazacLice.adresa, pronalazacLice.kontaktPodaci, pronalazacLice.ime, pronalazacLice.prezime, pronalazacLice.drzavljanstvo)
    }  
  }


  getPodnosilacPrijave(form: any) : PodnosilacZahteva{
    let lice : FizickoLice | PravnoLice;
    let kontakPodaci : KontaktPodaci = new KontaktPodaci(form.podnosilacPrijaveKontaktPodaci.faks, form.podnosilacPrijaveKontaktPodaci.email, form.podnosilacPrijaveKontaktPodaci.telefon)
    let adresa : Adresa = new Adresa(form.podnosilacPrijaveAdresa.broj, form.podnosilacPrijaveAdresa.ulica, form.podnosilacPrijaveAdresa.grad, form.podnosilacPrijaveAdresa.drzava, form.podnosilacPrijaveAdresa.postanskiBroj)
    if (form.podnosilacPrijaveFizickoLice){
      lice = new FizickoLice(adresa, kontakPodaci, form.podnosilacPrijaveFizickoLice.ime, form.podnosilacPrijaveFizickoLice.prezime, form.podnosilacPrijaveFizickoLice.drzavljanstvo)
    }
    else{
      lice = new PravnoLice(adresa, kontakPodaci, form.podnosilacPrijavePravnoLice.naziv)
    }
    let isPronalazac : boolean = form.patentFormParent.podnosilacJePronalazac
    return new PodnosilacZahteva(lice, isPronalazac);
  }


  
}
