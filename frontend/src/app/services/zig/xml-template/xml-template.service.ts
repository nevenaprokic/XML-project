import {Injectable} from '@angular/core';
import {
  Adresa,
  FizickoLice,
  KontaktPodaci, PodnosilacPrijave,
  PravnoLice, Punomocnik, VrstaZigaNaOsnovuIzgleda,
  ZahtevZaPriznanjeZiga, ZajednickiPredstavnik
} from "../../../model/zig";

@Injectable({
  providedIn: 'root'
})
export class XmlTemplateService {

  constructor() {
  }

  createNewXML(values: ZahtevZaPriznanjeZiga): string {
    const date = new Date();
    const dateForXML = `${date.getFullYear()}-0${date.getMonth()+1}-${date.getDate()}`;
    console.log(dateForXML);
    return `<?xml version="1.0" encoding="UTF-8"?>
    <Zahtev_za_priznanje_ziga broj_prijave_ziga="Ž-193/23" datum_podnosenja_prijave="${dateForXML}"  status="neobradjen"
                              xmlns="http://ftn.uns.ac.rs/zig"
                              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                              xsi:schemaLocation="http://ftn.uns.ac.rs/zig  ./zahtev_za_prijavu_ziga.xsd"
                              xmlns:zaj="http://www.ftn.uns.ac.rs/zaj">
        ${values.podnosiociPrijave.map(podnosilac => this.formatPodnosilacPrijave(podnosilac)).join(' \n')}
        ${this.formatPunomocnik(values.punomocnik)}
        ${this.formatZajednickiPredstavnik(values.zajednickiPredstavnik)}
        <Zig>
            <Vrsta_ziga_na_osnovu_korisnika>${values.zig.vrstaNaOsnovuKorisnika}</Vrsta_ziga_na_osnovu_korisnika>
            <Vrsta_ziga_na_osnovu_izgleda>${values.zig.vrstaNaOsnovuIzgleda !== VrstaZigaNaOsnovuIzgleda.DRUGO ? values.zig.vrstaNaOsnovuIzgleda : values.zig.vrstaNaOsnovuIzgledaStr}</Vrsta_ziga_na_osnovu_izgleda>
            <Izgled_znaka>${'nesto'}</Izgled_znaka>
            ${values.zig.bojaZnaka !== '' ? `<Podaci_o_boji_znaka>${values.zig.bojaZnaka}</Podaci_o_boji_znaka>` : '<Podaci_o_boji_znaka/>'}
            ${values.zig.transliteracijaZnaka !== '' ? `<Transliteracija_znaka>${values.zig.transliteracijaZnaka}</Transliteracija_znaka>` : '<Transliteracija_znaka/>'}
            ${values.zig.prevodZnaka !== '' ? `<Prevod_znaka>${values.zig.prevodZnaka}</Prevod_znaka>` : '<Prevod_znaka/>'}
            ${values.zig.opisZnaka !== '' ? `<Opis_znaka>${values.zig.opisZnaka}</Opis_znaka>` : '<Opis_znaka/>'}
            <Podaci_o_brojevima_klasa_robe_i_usluga>${values.zig.podaciOBrojevimaKlasaRobeIUsluga?.join(" ")}</Podaci_o_brojevima_klasa_robe_i_usluga>
        </Zig>
        ${values.pravoPrvenstvaIOsnov ? `<Pravo_prvenstva_i_osnov>${values.pravoPrvenstvaIOsnov}</Pravo_prvenstva_i_osnov>` : '<Pravo_prvenstva_i_osnov/>'}
        <Placene_takse>
            <Osnovna_taksa>${values.placeneTakse.osnovnaTaksa}</Osnovna_taksa>
            ${values.placeneTakse.zaGraficko > 0 ? `<Graficko_resenje>${values.placeneTakse.zaGraficko}</Graficko_resenje>` : '<Graficko_resenje/>'}
            ${values.placeneTakse.zaKlasu > 0 ? `<Za_klasu>${values.placeneTakse.zaKlasu}</Za_klasu>` : '<Za_klasu/>'}
            <Ukupan_iznos_takse>${values.placeneTakse.osnovnaTaksa + values.placeneTakse.zaGraficko + values.placeneTakse.zaKlasu}</Ukupan_iznos_takse>
        </Placene_takse>
        <Prilozi_uz_zahtev>
            <Primerak_znaka>
                <!--Optional:-->
                <Putanja_do_fajla>izgled_znaka.png</Putanja_do_fajla>
                <!--Optional:-->
                <Dostavljeno>true</Dostavljeno>
            </Primerak_znaka>
            <Spisak_robe_i_usluga>
                <!--Optional:-->
                <Putanja_do_fajla>spisak_robe.pdf</Putanja_do_fajla>
                <!--Optional:-->
                <Dostavljeno>true</Dostavljeno>
            </Spisak_robe_i_usluga>
            <!--You have a CHOICE of the next 3 items at this level-->
            <Punomocje>
                <!--Optional:-->
                <Putanja_do_fajla>podataka_o_punomocju.pdf</Putanja_do_fajla>
                <!--Optional:-->
                <Dostavljeno>true</Dostavljeno>
            </Punomocje>
            <Opsti_akt_o_kolektivnom_zigu_garancije>
                <!--Optional:-->
                <Putanja_do_fajla>opsti_akt.pdf</Putanja_do_fajla>
                <!--Optional:-->
                <Dostavljeno>true</Dostavljeno>
            </Opsti_akt_o_kolektivnom_zigu_garancije>
            <Dokaz_o_pravu_prvenstva>
                <!--Optional:-->
                <Putanja_do_fajla/>
                <!--Optional:-->
                <Dostavljeno>false</Dostavljeno>
            </Dokaz_o_pravu_prvenstva>
            <Dokaz_o_uplati_takse>
                <!--Optional:-->
                <Putanja_do_fajla>dokaz_o_uplati.pdf</Putanja_do_fajla>
                <!--Optional:-->
                <Dostavljeno>true</Dostavljeno>
            </Dokaz_o_uplati_takse>
        </Prilozi_uz_zahtev>

    </Zahtev_za_priznanje_ziga>
    `;
  }

  // formatPodnosilacPunomocnik(values: FizickoLice) {
  //     return `<Punomocnik>
  //           ${this.formatAdresa(values.adresa)}
  //           ${this.formatKontaktPodaci(values.kontaktPodaci)}
  //           <zaj:Ime>${values.ime}</zaj:Ime>
  //           <zaj:Prezime>${values.prezime}</zaj:Prezime>
  //         </Punomocnik>`
  // }
  //
  // formatPodnosilacPravnoLice(values: PravnoLice) {
  //     return `<Pravno_lice xsi:type="zaj:TPravno_lice">
  //             ${this.formatAdresa(values.adresa)}
  //             ${this.formatKontaktPodaci(values.kontaktPodaci)}
  //             <zaj:Naziv>${values.naziv}</zaj:Naziv>
  //         </Pravno_lice>`
  // }

  formatAdresa(values: Adresa | undefined) {
    return `<zaj:Adresa>
                <zaj:Ulica>${values?.ulica}</zaj:Ulica>
                <zaj:Broj>${values?.broj}</zaj:Broj>
                <zaj:Grad>${values?.grad}</zaj:Grad>
                <zaj:Drzava>${values?.drzava}</zaj:Drzava>
                <zaj:Postanski_broj>${values?.postanskiBroj}</zaj:Postanski_broj>
            </zaj:Adresa>`
  }

  formatKontaktPodaci(values: KontaktPodaci | undefined) {
    return ` <zaj:Kontakt_podaci>
                <zaj:Telefon>${values?.telefon}</zaj:Telefon>
                <zaj:Email>${values?.email}</zaj:Email>
                ${values?.faks ? `<zaj:Faks>${values?.faks}</zaj:Faks>` : ''}
            </zaj:Kontakt_podaci>`
  }

  private formatPodnosilacPrijave(podnosilac: PodnosilacPrijave) {
    return podnosilac.lice === "TFizicko_lice" ? this.formatPodnosilacPrijaveFizickoLice(podnosilac.podnosilacPrijave as FizickoLice)
      : this.formatPodnosilacPrijavePravnoLice(podnosilac.podnosilacPrijave as PravnoLice);
  }

  private formatPodnosilacPrijavePravnoLice(values: PravnoLice) {
    return `<Podnosioc_prijave xsi:type="zaj:TPravno_lice">
                ${this.formatAdresa(values.adresa)}
                ${this.formatKontaktPodaci(values.kontaktPodaci)}
                <zaj:Naziv>${values.naziv}</zaj:Naziv>
            </Podnosioc_prijave>
        `;
  }

  private formatPodnosilacPrijaveFizickoLice(values: FizickoLice) {
    return `<Podnosioc_prijave xsi:type="zaj:TFizicko_lice">
                ${this.formatAdresa(values.adresa)}
                ${this.formatKontaktPodaci(values.kontaktPodaci)}
                <zaj:Ime>${values.ime}</zaj:Ime>
                <zaj:Prezime>${values.prezime}</zaj:Prezime>
            </Podnosioc_prijave>
        `;
  }

  private formatPunomocnik(punomocnik: Punomocnik) {
    return punomocnik.lice === "TFizicko_lice" ? this.formatPunomocnikFizickoLice(punomocnik.punomocnik as FizickoLice)
      : this.formatPunomocnikPravnoLice(punomocnik.punomocnik as PravnoLice);
  }

  private formatZajednickiPredstavnik(zajednickiPredstavnik: ZajednickiPredstavnik) {
    return `<Zajednicki_predstavnik>
                   ${this.formatKontaktPodaci(zajednickiPredstavnik.kontaktPodaci)}
                   ${this.formatAdresa(zajednickiPredstavnik.adresa)}
                </Zajednicki_predstavnik>`;
  }

  private formatPunomocnikFizickoLice(values: FizickoLice) {
    return `<Punomocnik xsi:type="zaj:TFizicko_lice">
                ${this.formatAdresa(values.adresa)}
                ${this.formatKontaktPodaci(values.kontaktPodaci)}
                <zaj:Ime>${values.ime}</zaj:Ime>
                <zaj:Prezime>${values.prezime}</zaj:Prezime>
            </Punomocnik>
        `;
  }

  private formatPunomocnikPravnoLice(values: PravnoLice) {
    return `<Punomocnik xsi:type="zaj:TPravno_lice">
                ${this.formatAdresa(values.adresa)}
                ${this.formatKontaktPodaci(values.kontaktPodaci)}
                <zaj:Naziv>${values.naziv}</zaj:Naziv>
            </Punomocnik>
        `;
  }
}
