import { Injectable } from '@angular/core';
import { Adresa, FizickoLice, KontaktPodaci, PravnoLice } from 'src/app/model/common/common';
import { Podaci_o_dodatnoj_prijavi, Podaci_o_dostavljanju, PodnosilacZahteva, Pronalazac, Pronalazak, Punomcnik, RanijaPrijava, ZahtevZaPriznanjePatent, ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava } from 'src/app/model/patent/patent';

@Injectable({
  providedIn: 'root'
})
export class PatentXmlTemplateService {

  constructor() { }

  createNewXML(zahtev: ZahtevZaPriznanjePatent){
    return `<?xml version="1.0" encoding="UTF-8"?>
    <Zahtev_za_priznanje_patenta xmlns="http://www.ftn.uns.ac.rs/p1"
                                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                 xmlns:zaj="http://www.ftn.uns.ac.rs/zaj">
    
      <Primalac_zahteva>
      <zaj:Adresa>
        <zaj:Drzava>Republika Srbija</zaj:Drzava>
        <zaj:Grad>Beograd</zaj:Grad>
        <zaj:Ulica>Kneginje Ljubice</zaj:Ulica>
        <zaj:Broj>2</zaj:Broj>
        <zaj:Postanski_broj>11000</zaj:Postanski_broj>
      </zaj:Adresa>
      <Naziv>Zavod za intelektualnu svojinu</Naziv>
    </Primalac_zahteva>
     ${this.getPronalazk(zahtev.pronalazak)} 
     ${this.getPronalzac(zahtev.pronalazac)}
     ${this.formatPodnosilacZahteva(zahtev.podnosilac)}
     ${this.formatPunomocnik(zahtev.punomocnik)}                           
     ${this.formatPodaciODostavljanje(zahtev.podaciDostavljanja)}          
     ${this.formatZahtevZaPrvenstvo(zahtev.prvenstvo)}
     ${this.formatPodaciORanijojPrijavi(zahtev.podaciORanijojPrijavi)}
     </Zahtev_za_priznanje_patenta>
     `                   
  }

  getPronalazk(pronalazak: Pronalazak) {
    return `<Pronalazak>
    <Naziv_na_srpskom>${pronalazak.nazivNaSrpskom}</Naziv_na_srpskom>
    <Naziv_na_engleskom>${pronalazak.nazivNaEngleskom}</Naziv_na_engleskom>
    </Pronalazak>`
  }

  getPronalzac(pronalazac: Pronalazac){
    return `<Pronalazac anoniman="${pronalazac.anoniman}">
    ${this.formatFizickolice(pronalazac)}
    </Pronalazac>`
  }

  formatPodnosilacZahteva(podnosilac: PodnosilacZahteva){
    let tipLica: string = podnosilac.lice instanceof FizickoLice? "TFizicko_lice" : "TPravno_lice" 
    let lice = podnosilac.lice instanceof FizickoLice? this.formatFizickolice(podnosilac.lice) : this.formatPravnoLice(podnosilac.lice)
    return `<Podnosilac_zahteva>
              <Lice xsi:type="zaj:${tipLica}">  
                ${lice}
              </Lice>
              <pronalazac>${podnosilac.pronalazac}</pronalazac>
        </Podnosilac_zahteva>`
  }

  formatPunomocnik(punomocnik : Punomcnik | undefined){
    if(punomocnik){
      let tipLica: string = punomocnik.lice instanceof FizickoLice? "TFizicko_lice" : "TPravno_lice" 
      let lice = punomocnik.lice instanceof FizickoLice? this.formatFizickolice(punomocnik.lice) : this.formatPravnoLice(punomocnik.lice)
      return `<Punomocnik>
              <Lice xsi:type="zaj:${tipLica}">  
                ${lice}
              </Lice>
      </Punomocnik>`
    }
    return ""
  }

  formatPodaciODostavljanje(podaciDostavljanja: Podaci_o_dostavljanju){
    return `<Podaci_o_dostavljanju>
            ${this.formatAdresa(podaciDostavljanja.adresa)}
            <Nacin_dostavljanja>${podaciDostavljanja.nacinDostavljanja}</Nacin_dostavljanja>
        </Podaci_o_dostavljanju>`
  }

  formatZahtevZaPrvenstvo(prvenstvo: ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava | undefined){
    if(prvenstvo){
      return `<Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava>
              ${this.formatRanijePrijave(prvenstvo.ranijePrijave)}
        </Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava>`
    }
    return ""
  }

  formatRanijePrijave(ranijePrijave: RanijaPrijava[]){
      let ranijePrijaveXML = ""
      ranijePrijave.forEach((prijava)=> {
        ranijePrijaveXML += this.formatRanijaPrijava(prijava)
      })
  }

  formatRanijaPrijava(ranijaPrijava: RanijaPrijava){
    return `<Ranija_prijava>
              <Datum_podnosenja>${ranijaPrijava.datumPodnosenjaPrijave}</Datum_podnosenja>
              <Broj_prijave>${ranijaPrijava.brojPrijave}</Broj_prijave>
              <Dvoslovna_oznaka_drzave>${ranijaPrijava.DvoslovnaOznakaDrzave}</Dvoslovna_oznaka_drzave>
            </Ranija_prijava>`
   }

   formatPodaciORanijojPrijavi(ranijaPrijava : Podaci_o_dodatnoj_prijavi | undefined){
    if (ranijaPrijava){
      return `<Podaci_o_dodatnoj_prijavi>
                <Tip_prijave>${ranijaPrijava.tipPrijave}</Tip_prijave>
                <Broj_prvobitne_prijave>${ranijaPrijava.brojPrvobitnePrijave}</Broj_prvobitne_prijave>
                <Datum_podnosenja_prvobitne_prijave>${ranijaPrijava.datumPodnosenjaPrvobitnePrijave}</Datum_podnosenja_prvobitne_prijave>
              </Podaci_o_dodatnoj_prijavi>`
    }
    return ""
   }

  private formatFizickolice(values: FizickoLice) {
    return `
                ${this.formatAdresa(values.adresa)}
                ${this.formatKontaktPodaci(values.kontaktPodaci)}
                <zaj:Ime>${values.ime}</zaj:Ime>
                <zaj:Prezime>${values.prezime}</zaj:Prezime>
        `;
  }

  private formatPravnoLice(values: PravnoLice) {
    return `
                ${this.formatAdresa(values.adresa)}
                ${this.formatKontaktPodaci(values.kontaktPodaci)}
                <zaj:Naziv>${values.naziv}</zaj:Naziv>
        `;
  }

  
  formatAdresa(values: Adresa | undefined) {
    return `<zaj:Adresa>
                <zaj:Drzava>${values?.drzava}</zaj:Drzava>
                <zaj:Grad>${values?.grad}</zaj:Grad>
                <zaj:Ulica>${values?.ulica}</zaj:Ulica>
                <zaj:Broj>${values?.broj}</zaj:Broj>
                <zaj:Postanski_broj>${values?.postanskiBroj}</zaj:Postanski_broj>
            </zaj:Adresa>`
  }

  formatKontaktPodaci(values: KontaktPodaci | undefined) {
    return ` <zaj:Kontakt_podaci>
                <zaj:Telefon>${values?.telefon}</zaj:Telefon>
                ${values?.faks ? `<zaj:Faks>${values?.faks}</zaj:Faks>` : ''}
                <zaj:Email>${values?.email}</zaj:Email>  
            </zaj:Kontakt_podaci>`
  }
}
