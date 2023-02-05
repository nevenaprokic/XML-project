import { Injectable } from '@angular/core';
import {  FizickoLice, KontaktPodaci, PravnoLice } from 'src/app/model/common/common';
import { PodnosilacPrijave, Punomocnik, TTakse, TZig, ZahtevZaPriznanjeZiga, ZajednickiPredstavnik, Adresa, TPrilozi, TDopuna } from 'src/app/model/zig';

@Injectable({
  providedIn: 'root'
})
export class ZigXmlConverterService {

  constructor() { }

  prefix : string = "";
  commonPrefix : string = "";

  getZigFromXML(xml: any, prefix: string, commonPrefix: string): ZahtevZaPriznanjeZiga {
    this.prefix = prefix;
    this.commonPrefix = commonPrefix;
    const takse : TTakse = this.getPlaceneTakse(xml);
    const podnosioci : PodnosilacPrijave[] = this.getPodnosiociPrijave(xml);
    const prvenstvo : string = xml[this.prefix + ':Pravo_prvenstva_i_osnov']? xml[this.prefix + ':Pravo_prvenstva_i_osnov']._text : ""
    const prilozi : TPrilozi = this.getPrilozi(xml[this.prefix + ':Prilozi_uz_zahtev']);
    const punomocnik = this.getPunomocnik(xml)
    const zig = this.getZig(xml)
    const zajPredstavnik = this.getZajednickiPredstavnik(xml)
    const idZiga = xml[this.prefix + ":idZiga"];
    const id = idZiga[prefix + ':idZ']._text;

    const attributes = xml["_attributes"]
    const brojprijave = attributes["broj_prijave_ziga"]
    const status = attributes["status"]
    const datumPodnosenja = attributes["datum_podnosenja_prijave"]

    let podnosiociStr = '';
    podnosioci.forEach((podnosilac) => {
      podnosiociStr += podnosilac.podnosilacPrijave.kontaktPodaci.email + ", "
    })

    return {
      placeneTakse: takse,
      podnosiociPrijave: podnosioci,
      pravoPrvenstvaIOsnov: prvenstvo,
      punomocnik: punomocnik,
      zig: zig,
      zajednickiPredstavnik: zajPredstavnik,
      id: id,
      brojZahteva : brojprijave,
      datumPodnosenjaZahteva: datumPodnosenja,
      status: status,
      podnosiociStr: podnosiociStr,
      priloziUzZahtev: prilozi
    }
  }

  getPlaceneTakse(xml:any) : TTakse{
    let takse = xml[this.prefix + ':Placene_takse']
    let osnovnaTaksa = takse[this.prefix + ':Osnovna_taksa']._text
    let ukupnaTaksa = takse[this.prefix + ':Ukupan_iznos_takse']._text
    let zaKlasu = takse[this.prefix + ':Za_klasu'] ? takse[this.prefix + ':Za_klasu']._text : 0
    let zaGraficko = takse[this.prefix + ':Graficko_resenje'] ? takse[this.prefix + ':Graficko_resenje']._text : 0;
    return {
      osnovnaTaksa : osnovnaTaksa,
      zaGraficko: zaGraficko,
      zaKlasu: zaKlasu,
      ukupno: ukupnaTaksa
    }
  }

  getPodnosiociPrijave(xml:any) : PodnosilacPrijave[]{
    let podnosiociXML = xml[this.prefix + ':Podnosioc_prijave']
    let podnosioci : PodnosilacPrijave[] =[]
    if(Array.isArray(podnosiociXML)){
      podnosiociXML.forEach((podnosilac:any) => {
        podnosioci.push(this.getPodnsoilac(podnosilac))
      })
    }
    else{
      podnosioci.push(this.getPodnsoilac(podnosiociXML))
    }
    return podnosioci;
  }

  getPodnsoilac(xml:any):PodnosilacPrijave{
    let lice = this.getLice(xml)
    return {
      podnosilacPrijave: lice
    }
  }

  getLice(xml: any): FizickoLice | PravnoLice{
    let lice : FizickoLice | PravnoLice;
    if (xml['_attributes']['xsi:type'].substring(4) === "TPravno_lice"){
      lice = this.getPravnoLice(xml)
    }
    else{
      lice = this.getFizickoLice(xml);
    }
    return lice;
  }

  getPunomocnik(xml: any) : Punomocnik | undefined{
    let punomocnik = xml[this.prefix + ":Punomocnik"];
    console.log(punomocnik);
    if (punomocnik){
      let lice = this.getLice(punomocnik)
      return {punomocnik : lice}
    }
    return undefined
  }

  getZajednickiPredstavnik(xml:any) : ZajednickiPredstavnik | undefined{
    let predstvnik = xml[this.prefix + ':Zajednicki_predstavnik']
    if (predstvnik){
      let kontaktPodaci = this.getKontaktPodaci(predstvnik);
      let adresa = this.getAdresa(predstvnik);
      return {
        kontaktPodaci: kontaktPodaci,
        adresa: adresa
      }
    }
    return undefined
  }

  getZig(xml:any) : TZig{
    let zig = xml[this.prefix + ":Zig"]
    let vrstaPoIzgledu = zig[this.prefix + ":Vrsta_ziga_na_osnovu_izgleda"]._text
    let vrstaPoKorisniku = zig[this.prefix + ":Vrsta_ziga_na_osnovu_korisnika"]._text
    let izgled = zig[this.prefix + ":Izgled_znaka"]? zig[this.prefix + ":Izgled_znaka"]._text : ""
    let opis = zig[this.prefix + ":Opis_znaka"]? zig[this.prefix + ":Opis_znaka"]._text : ""
    let podaciOBoji = zig[this.prefix + ":Podaci_o_boji_znaka"]? zig[this.prefix + ":Podaci_o_boji_znaka"]._text : ""
    let prevodZnaka = zig[this.prefix + ":Prevod_znaka"]? zig[this.prefix + ":Prevod_znaka"]._text : ""
    let transliteracijaZnaka = zig[this.prefix + ":Transliteracija_znaka"]? zig[this.prefix + ":Transliteracija_znaka"]._text : ""
    let podaciOBrojevimaKlasaRobeIUsluga = zig[this.prefix + ":Podaci_o_brojevima_klasa_robe_i_usluga"]._text.split(" ").map((br:string)=> Number(br))

    return {
      vrstaNaOsnovuKorisnika: vrstaPoKorisniku,
        vrstaNaOsnovuIzgleda: vrstaPoIzgledu,
        izgledZnaka: izgled,
        bojaZnaka: podaciOBoji,
        transliteracijaZnaka: transliteracijaZnaka,
        prevodZnaka: prevodZnaka,
        opisZnaka: opis,
        podaciOBrojevimaKlasaRobeIUsluga: podaciOBrojevimaKlasaRobeIUsluga
    }
  }

  getFizickoLice(xml:any) :FizickoLice{
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
    let kontakt = xml[this.commonPrefix + ':Kontakt_podaci'];
     return new KontaktPodaci(kontakt[this.commonPrefix + ':Faks']?._text, kontakt[this.commonPrefix + ':Email']._text, kontakt[this.commonPrefix + ':Telefon']._text)
  }

  getAdresa(xml:any):Adresa{
    let adresa = xml[this.commonPrefix + ':Adresa']
    return { broj: adresa[this.commonPrefix + ':Broj']._text,
    ulica: adresa[this.commonPrefix + ':Ulica']._text,
    grad: adresa[this.commonPrefix + ':Grad']._text,
    drzava : adresa[this.commonPrefix + ':Drzava']? adresa[this.commonPrefix + ':Drzava']._text : "",
    postanskiBroj: adresa[this.commonPrefix + ':Postanski_broj']? adresa[this.commonPrefix + ':Postanski_broj']._text: ""}
 }

 getPrilozi(xml: any) : TPrilozi{
  return {
    dokazOPravuPrvenstva: this.getPrilog(xml[this.prefix + ":Dokaz_o_pravu_prvenstva"]),
    dokazOUplatiTakse: this.getPrilog(xml[this.prefix + ":Dokaz_o_uplati_takse"]),
    opstiAktOKolektivnomZiguGarancije: this.getPrilog(xml[this.prefix + ":Opsti_akt_o_kolektivnom_zigu_garancije"]),
    primerakZnaka: this.getPrilog(xml[this.prefix + ":Primerak_znaka"]),
    punomocje: this.getPrilog(xml[this.prefix + ":Punomocje"]),
    punomocjeNaknadnoDostavljeno: this.getPrilog(xml[this.prefix + ":Spisak_robe_i_usluga"]),
    punomocjeRanijePrilozeno: this.getPrilog(xml[this.prefix + ":Dokaz_o_uplati_takse"]),
    spisakRobeIUsluga: this.getPrilog(xml[this.prefix + ":Spisak_robe_i_usluga"]),
  }
}
  getPrilog(xml: any): TDopuna {
    if(!xml || Object.keys(xml).length <= 1){
      return {dostavljeno: false, putanjaDoFajla: ''}
    }
    let putanjaElem = xml[this.prefix + ":Putanja_do_fajla"]
    let putanja = (Object.keys(putanjaElem).length >= 1 && putanjaElem) ? putanjaElem._text : '';
    return {dostavljeno: putanja!=='', putanjaDoFajla: putanja}
  }
}
