import { Injectable } from '@angular/core';
import { MatOptionSelectionChange } from '@angular/material/core';
import { Adresa, Autor, AutroskoDelo, Identifikator, KontaktPodaci, PodaciOOriginalu, ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';
import { FizickoLice, PravnoLice } from 'src/app/model/common/common';

@Injectable({
  providedIn: 'root'
})
export class AutorskoDeloXmlConvertorService {

  prefix : string = "";
  commonPrefix : string = '';
  constructor() { }

  getAutoskoDeloFromXML(xml: any, prefix: string, commonPrefix: string) : ZahtevZaAutorskoDelo{
    this.prefix = prefix;
    this.commonPrefix = commonPrefix;
    let atributes = xml['_attributes']
    let brojPrijave = atributes["broj_prijave"];
    let datumPodnosenja = atributes["datum_podnosenja"]
    let naslov = atributes["naslov"]
    let status = atributes["status"]
    let podnosilac : {autor: Autor | undefined, pravnoLice: PravnoLice | undefined, FizickoLice: FizickoLice | undefined,} = this.getPodnosilac(xml)
    let id = xml[this.prefix + ":idAutorskogDela"][this.prefix + ":idA"]._text;
    let autorskoDelo : AutroskoDelo = this.getAutoskoDelo(xml);
    let prilozi : {opis: string, primer: string} = this.getPrilozi(xml);
    let podnosilacnaziv = podnosilac.autor ? podnosilac.autor.ime : podnosilac.pravnoLice ? podnosilac.pravnoLice.naziv : podnosilac.FizickoLice?.ime

    return {
      podnosilacAutor : podnosilac.autor,
      podnosilacFizickoLice: podnosilac.FizickoLice,
      podnosilacPravnoLice: podnosilac.pravnoLice,
      autorskoDelo: autorskoDelo,
      opis: prilozi.opis,
      primer: prilozi.primer,
      id: id,
      status: status,
      naslov: naslov,
      brojPrijave: brojPrijave,
      datumPodnosenja: datumPodnosenja,
      podnosilacNaziv: podnosilacnaziv
    }
  }

  getAutoskoDelo(xml: any) : AutroskoDelo{
    let autorskoDelo = xml[this.prefix + ":Autorsko_delo"]
    let autori : Autor[] = this.getAutori(autorskoDelo)
    let identifikator = this.getIdentifikator(autorskoDelo)
    let nacinKoriscenja = autorskoDelo[this.prefix + ":Nacin_koriscenja"] ? autorskoDelo[this.prefix + ":Nacin_koriscenja"]._text : "";
    let radniOdnos = autorskoDelo[this.prefix + ":Radni_odnos"] ? autorskoDelo[this.prefix + ":Radni_odnos"]._text : "";
    let podaciOOriginalu : PodaciOOriginalu | undefined = this.getPodaciOOriginalu(xml)

    let attributes = autorskoDelo['_attributes']
    let vrsta = attributes['vrsta']
    let forma_zapisa = attributes['forma_zapisa']
    let prerada = attributes['prerada']
    let id = attributes['id']
    
    return {
      autori: autori,
      identifikator: identifikator,
      nacinKoriscenja: nacinKoriscenja,
      radniOdnos: radniOdnos,
      podaciOOriginalu: podaciOOriginalu,
      vrsta: vrsta,
      formaZapisa: forma_zapisa,
      prerada: prerada,
    }
  }

  getAutori(xml:any) : Autor[]{
    let autoriXML = xml[this.prefix + ":Autori"]
    let autori: Autor[] = []
    if (Object.keys(autoriXML).length === 0){
      return autori;
    }
    let autoriList : any[] = autoriXML[this.prefix + ":Autor"]
    if(Array.isArray(autoriList)){
      autoriList.forEach((autorXML) => {
        let autor : Autor = this.getAutor(autorXML);
        autori.push(autor);
      })
    }
    else{
      let autor : Autor = this.getAutor(autoriList);
        autori.push(autor);
    }
    return autori;

  }

  getAutor(xml:any):Autor{
    let adresa : Adresa = this.getAdresa(xml)
    let kontaktPodaci : KontaktPodaci = this.getKontaktPodaci(xml)
    let ime = xml[this.commonPrefix + ":Ime"] ?  xml[this.commonPrefix + ":Ime"]._text : "" 
    let prezime = xml[this.commonPrefix + ":Prezime"]?  xml[this.commonPrefix + ":Prezime"]._text : ""
    let drzavljanstvo = xml[this.commonPrefix + ":Drzavljanstvo"]?  xml[this.commonPrefix + ":Drzavljanstvo"]._text : ""
    let attributes = xml["_attributes"]
    let anonimni = attributes['anonimni'] === 'true'
    let primarni = attributes['primarni'] === 'true'
    let pseudonim = xml[this.prefix + ':Pseudonim'] ?  xml[this.prefix + ':Pseudonim']._text : undefined
    let godinaSmrti =  xml[this.prefix + ':Godina_smrti'] ?  xml[this.prefix + ':Godina_smrti']._text : undefined;

    let autor : Autor = {
      ime : ime,
      prezime: prezime,
      adresa: adresa,
      kontaktPodaci: kontaktPodaci,
      anonimni: anonimni,
      primarni: primarni,
      godinaSmrti: godinaSmrti,
      drzavljanstvo: drzavljanstvo,
      pseudonim: pseudonim
    }

    return autor;
  }

  getIdentifikator(xml: any):Identifikator{
    let identifikator = xml[this.prefix + ':Identifikator']
    return{ alternativniNaslov: identifikator[this.prefix + ":Alternativni_naslov"] ? identifikator[this.prefix + ":Alternativni_naslov"]._text : "",
          naslov: identifikator[this.prefix + ":Naslov"]._text }
  }

  getPodaciOOriginalu(xml: any) : PodaciOOriginalu | undefined{
    let podaci = xml[this.prefix + ":Podaci_o_originalu"]
    if (podaci) {
      let autori : Autor[] = this.getAutori(podaci)
      let identifikator = this.getIdentifikator(podaci);
      return {
        autori: autori,
        identifikator: identifikator
      }
    }
    return undefined
  }

  getPodnosilac(xml: any) : {autor: Autor | undefined, pravnoLice: PravnoLice | undefined, FizickoLice: FizickoLice | undefined,} {
    let podnosilac = xml[this.prefix + ":Podnosilac"]
    let autor = podnosilac[this.prefix + ":Autor"]
    let pravnoLice = podnosilac[this.prefix + ":Pravno_lice"]
    let punomocnik = podnosilac[this.prefix + ":Punomocnik"]
    
    return {
      autor : Object.keys(autor).length > 1 ? this.getAutor(autor) : undefined,
      pravnoLice : Object.keys(pravnoLice).length > 1 ? this.getPravnoLice(pravnoLice) : undefined,
      FizickoLice : Object.keys(punomocnik).length > 1 ? this.getFizickoLice(punomocnik) : undefined,
    }
  }

  getPrilozi(xml: any) : {opis: string, primer: string}{
    let prilozi = xml[this.prefix + ":Prilozi"]
    let prisutanOpis = prilozi[this.prefix + ":Prisutan_opis"]
    let opis = Object.keys(prisutanOpis).length > 1 ? prisutanOpis[this.prefix + ":Putanja_do_fajla"]._text : undefined;
    let prisutanPrimer = prilozi[this.prefix + ":Prisutan_primer"]
    let primer = Object.keys(prisutanPrimer).length > 1 ? prisutanPrimer[this.prefix + ":Putanja_do_fajla"]._text : undefined;
    return {
      opis: opis,
      primer :primer
    }
  }


  getAdresa(xml:any):Adresa{
    let adresa = xml[this.commonPrefix + ':Adresa']
    return {
      broj: adresa[this.commonPrefix + ':Broj']._text,
      ulica: adresa[this.commonPrefix + ':Ulica']._text,
      grad: adresa[this.commonPrefix + ':Grad']._text
    }
 }

 getKontaktPodaci(xml: any) : KontaktPodaci{
  let kontakt = xml[this.commonPrefix + ':Kontakt_podaci']
   return {email: kontakt[this.commonPrefix + ':Email']._text, 
          telefon: kontakt[this.commonPrefix + ':Telefon']._text,
          faks: kontakt[this.commonPrefix + ':Faks'] ? kontakt[this.commonPrefix + ':Faks']._text : ""}
}

getPravnoLice(xml:any): PravnoLice{
  let kontaktPodaci : KontaktPodaci = this.getKontaktPodaci(xml);
  let adresa: Adresa = this.getAdresa(xml);
  let naziv: string = xml[this.commonPrefix + ':Naziv']._text;
  return new PravnoLice(adresa, kontaktPodaci, naziv);
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
}
