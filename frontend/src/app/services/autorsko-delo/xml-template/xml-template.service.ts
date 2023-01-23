import { Injectable } from '@angular/core';
import { Adresa, Autor, AutroskoDelo, FizickoLice, Identifikator, KontaktPodaci, PravnoLice, ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';

@Injectable({
  providedIn: 'root'
})
export class XMLTemplateService {
  
  constructor() { }

  createNewXML(values: ZahtevZaAutorskoDelo) : string {
    return `<?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="../xsl/grddl.xsl"?>
    
    <Zahtev_za_autorsko_delo 
      xmlns="http://ftn.uns.ac.rs/a1" 
      xmlns:zaj="http://www.ftn.uns.ac.rs/zaj" 
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://ftn.uns.ac.rs/a1 ./A1.xsd"
    
        naslov="ZAHTEV ZA UNOSENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA"
        broj_prijave='A-100'
        datum_podnosenja='${new Date()}'
        status="neobradjen">
    
        <Zavod>
          <zaj:Adresa>
            <zaj:Grad>Beograd</zaj:Grad>
            <zaj:Ulica>Kneginje Ljubice</zaj:Ulica>
            <zaj:Broj>2</zaj:Broj>
          </zaj:Adresa>
          <Naziv>Zavod za intelektualnu svojinu</Naziv>
        </Zavod>
    
        <Podnosilac>
          ${values.podnosilacAutor ? this.formatAutor(values.podnosilacAutor): ''}
          ${values.podnosilacPravnoLice ? this.formatPodnosilacPravnoLice(values.podnosilacPravnoLice): ''}
          ${values.podnosilacFizickoLice ? this.formatPodnosilacPunomocnik(values.podnosilacFizickoLice): ''}
        </Podnosilac>
    
        ${this.formatAutorskoDelo(values.autorskoDelo)}
        
        
        <Prilozi>
            <Prisutan_opis>${values.opis}</Prisutan_opis>
            <Prisutan_primer>${values.primer}</Prisutan_primer>
        </Prilozi>
    
    </Zahtev_za_autorsko_delo>`
  }

  formatAutorskoDelo(values: AutroskoDelo){
    const a = values.autori.map(autor => this.formatAutor(autor)).join(' ');

    return `
    <Autorsko_delo 
      ${values.vrsta ? `vrsta='${values.vrsta}'`: ''} 
      ${values.formaZapisa ? `forma_zapisa='${values.formaZapisa}'`: ''} 
      ${values.prerada ? `prerada='${values.prerada}'`: ''} >
            
       ${this.formatIdentifikator(values.identifikator)}
        
        <Autori>
            ${a}
        </Autori>
        
        <Podaci_o_originalu>
            ${this.formatIdentifikator(values.podaciOOriginalu.identifikator)}
        </Podaci_o_originalu>
        
        <Radni_odnos>${values.radniOdnos}</Radni_odnos>
        <Nacin_koriscenja>${values.nacinKoriscenja}</Nacin_koriscenja>
        
    </Autorsko_delo>
    `//.replaceAll('\n', '').replaceAll('\r', '')

    // Delo stvoreno u radnom odnosu sa FTN-om u Novom Sadu
    // Delo ce biti korisceno u naucne svrhe
  }

  formatIdentifikator(values: Identifikator){
    return `
    <Identifikator>
      <Naslov>${values.naslov}</Naslov>
      <Alternativni_naslov>${values.alternativniNaslov}</Alternativni_naslov>
    </Identifikator>
    `
  }


  formatPodnosilacPunomocnik(values: FizickoLice){
    return `
    <Punomocnik>
      ${this.formatAdresa(values.adresa)}
      ${this.formatKontaktPodaci(values.kontaktPodaci)}
      <zaj:Ime>${values.ime}</zaj:Ime>
      <zaj:Prezime>${values.prezime}</zaj:Prezime>
    </Punomocnik>`
  }

  formatPodnosilacPravnoLice(values: PravnoLice){
    return `
    <Pravno_lice xsi:type="zaj:TPravno_lice">
        ${this.formatAdresa(values.adresa)}
        ${this.formatKontaktPodaci(values.kontaktPodaci)}
        <zaj:Naziv>${values.naziv}</zaj:Naziv>
    </Pravno_lice>`
  }

  formatAutor(values: Autor) {
    return `
    <Autor xsi:type="TAutor" anonimni='${values.anonimni ? true : false}' primarni='${values.primarni ? true : false}'>
      ${this.formatAdresa(values.adresa)}
      ${this.formatKontaktPodaci(values.kontaktPodaci)}
      <zaj:Ime>${values.ime}</zaj:Ime>
      <zaj:Prezime>${values.prezime}</zaj:Prezime>
      
      ${values.drzavljanstvo ? `<zaj:Drzavljanstvo>${values.drzavljanstvo}</zaj:Drzavljanstvo>`: ''}>

      ${values.pseudonim ? `<Pseudonim>${values.pseudonim}</Pseudonim>`: ''}>
      ${values.godinaSmrti ? `<Godina_smrti>${values.godinaSmrti}</Godina_smrti>`: ''}>
    </Autor>`
  }

  formatAdresa(values: Adresa | undefined) {
    return `
    <zaj:Adresa>
      <zaj:Grad>${values?.grad}</zaj:Grad>
      <zaj:Ulica>${values?.ulica}</zaj:Ulica>
      <zaj:Broj>${values?.broj}</zaj:Broj>
    </zaj:Adresa>`
  }

  formatKontaktPodaci(values: KontaktPodaci | undefined) {
    return `
    <zaj:Kontakt_podaci>
        <zaj:Telefon>${values?.telefon}</zaj:Telefon>
        <zaj:Email>${values?.email}</zaj:Email>
        ${values?.faks ? `<zaj:Faks>${values?.faks}</zaj:Faks>` : '' }
    </zaj:Kontakt_podaci>`
  }
}
