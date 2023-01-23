import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ZahtevType } from 'src/app/model/model';

@Injectable({
  providedIn: 'root'
})
export class XMLTemplateService {
  
  constructor() { }

  createNewXML(values: any) : string {
    return `<?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="../xsl/grddl.xsl"?>
    
    <Zahtev_za_autorsko_delo 
      xmlns="http://ftn.uns.ac.rs/a1" 
      xmlns:zaj="http://www.ftn.uns.ac.rs/zaj" 
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://ftn.uns.ac.rs/a1 ./A1.xsd"
    
        naslov="ZAHTEV ZA UNOSENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA"
        broj_prijave='A-100'
        datum_podnosenja="2022-12-08"
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
           <Autor primarni="true" xsi:type="TAutor">
                <zaj:Adresa>
                     <zaj:Grad>Novi Sad</zaj:Grad>
                     <zaj:Ulica>Maksima Gorkog</zaj:Ulica>
                     <zaj:Broj>46</zaj:Broj>
                 </zaj:Adresa>
                 <zaj:Kontakt_podaci>
                   <zaj:Telefon>065/222-3332</zaj:Telefon>
                <zaj:Email>kk@gmail.com</zaj:Email>
                </zaj:Kontakt_podaci>
                 <zaj:Ime>Katarina</zaj:Ime>
                 <zaj:Prezime>Komad</zaj:Prezime>
                
                 <zaj:Drzavljanstvo>Srpsko</zaj:Drzavljanstvo>
            </Autor>
           
        </Podnosilac>
    
        <Autorsko_delo vrsta="racunarski program" forma_zapisa="ostalo" prerada="true">
            
            <Identifikator>
                <Naslov>XML</Naslov>
                <Alternativni_naslov>A1</Alternativni_naslov>
            </Identifikator>
            
            <Autori>
                <Autor primarni="true" xsi:type="TAutor">
                   <zaj:Adresa>
                        <zaj:Grad>Novi Sad</zaj:Grad>
                        <zaj:Ulica>Maksima Gorkog</zaj:Ulica>
                        <zaj:Broj>46</zaj:Broj>
                    </zaj:Adresa>
                    <zaj:Kontakt_podaci>
                      <zaj:Telefon>065/222-3332</zaj:Telefon>
                    <zaj:Email>kk@gmail.com</zaj:Email>
                  </zaj:Kontakt_podaci>
                    <zaj:Ime>Katarina</zaj:Ime>
                    <zaj:Prezime>Komad</zaj:Prezime>
                   
                    <zaj:Drzavljanstvo>Srpsko</zaj:Drzavljanstvo>
                </Autor>
    
                <Autor anonimni="true" primarni="false">
                    <zaj:Adresa>
                        <zaj:Grad>Nis</zaj:Grad>
                        <zaj:Ulica>Bulevar Oslobodjenja</zaj:Ulica>
                        <zaj:Broj>1</zaj:Broj>
                    </zaj:Adresa>
                    <zaj:Kontakt_podaci>
                      <zaj:Telefon>065/222-3332</zaj:Telefon>
                    <zaj:Email>jhon.doe@gmail.com</zaj:Email>
                  </zaj:Kontakt_podaci>
                    <zaj:Ime>Jhon</zaj:Ime>
                    <zaj:Prezime>Doe</zaj:Prezime>
                   
                    <zaj:Drzavljanstvo>Srpsko</zaj:Drzavljanstvo>
                    
                    <Pseudonim>Anonimus</Pseudonim>
                    <Godina_smrti>2010</Godina_smrti>
                </Autor>
            </Autori>
            
            <Podaci_o_originalu>
                <Identifikator>
                    <Naslov>XML-01</Naslov>
                </Identifikator>
            </Podaci_o_originalu>
            
            <!-- <Radni_odnos> Delo stvoreno u radnom odnosu sa FTN-om u Novom Sadu</Radni_odnos> -->
            <Nacin_koriscenja>Delo ce biti korisceno u naucne svrhe</Nacin_koriscenja>
            
        </Autorsko_delo>
        
        
        <Prilozi>
            <Prisutan_opis>${values.opis}</Prisutan_opis>
            <Prisutan_primer>${values.primer}</Prisutan_primer>
        </Prilozi>
    
    </Zahtev_za_autorsko_delo>`
  }
}
