import {Injectable} from '@angular/core';
import {
  Adresa,
  FizickoLice,
  KontaktPodaci, PodnosilacPrijave,
  PravnoLice, Punomocnik, TPrilozi, VrstaZigaNaOsnovuIzgleda,
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
    const dateForXML = date.getFullYear()+ "-" +  ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
    console.log(dateForXML);
    return `<?xml version="1.0" encoding="UTF-8"?>
    <Zahtev_za_priznanje_ziga datum_podnosenja_prijave="${dateForXML}"  status="neobradjen"
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
          ${this.formatPrilozi(values.priloziUzZahtev)}
        </Prilozi_uz_zahtev>

    </Zahtev_za_priznanje_ziga>
    `;
  }
  formatPrilozi(values: TPrilozi) {
    return `
    <Primerak_znaka>
        <!--Optional:-->
        ${values.primerakZnaka?.putanjaDoFajla ? `<Putanja_do_fajla>${values.primerakZnaka.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.primerakZnaka?.dostavljeno}</Dostavljeno>
    </Primerak_znaka>
    <Spisak_robe_i_usluga>
        <!--Optional:-->
        ${values.spisakRobeIUsluga?.putanjaDoFajla ? `<Putanja_do_fajla>${values.spisakRobeIUsluga.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.spisakRobeIUsluga?.dostavljeno}</Dostavljeno>
    </Spisak_robe_i_usluga>
    <!--You have a CHOICE of the next 3 items at this level-->
    <Punomocje>
        <!--Optional:-->
        ${values.punomocje?.putanjaDoFajla ? `<Putanja_do_fajla>${values.punomocje.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.punomocje?.dostavljeno}</Dostavljeno>
    </Punomocje>
    <Punomocje_ranije_prilozeno>
        <!--Optional:-->
        ${values.punomocjeRanijePrilozeno?.putanjaDoFajla ? `<Putanja_do_fajla>${values.punomocjeRanijePrilozeno.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.punomocjeRanijePrilozeno?.dostavljeno}</Dostavljeno>
    </Punomocje_ranije_prilozeno>
    <Punomocje_naknadno_dostavljeno>
        <!--Optional:-->
        ${values.punomocjeNaknadnoDostavljeno?.putanjaDoFajla ? `<Putanja_do_fajla>${values.punomocjeNaknadnoDostavljeno.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.punomocjeNaknadnoDostavljeno?.dostavljeno}</Dostavljeno>
    </Punomocje_naknadno_dostavljeno>
    <Opsti_akt_o_kolektivnom_zigu_garancije>
        <!--Optional:-->
        ${values.opstiAktOKolektivnomZiguGarancije?.putanjaDoFajla ? `<Putanja_do_fajla>${values.opstiAktOKolektivnomZiguGarancije.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.opstiAktOKolektivnomZiguGarancije?.dostavljeno}</Dostavljeno>
    </Opsti_akt_o_kolektivnom_zigu_garancije>
    <Dokaz_o_pravu_prvenstva>
        <!--Optional:-->
        ${values.dokazOPravuPrvenstva?.putanjaDoFajla ? `<Putanja_do_fajla>${values.dokazOPravuPrvenstva.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.dokazOPravuPrvenstva?.dostavljeno}</Dostavljeno>
    </Dokaz_o_pravu_prvenstva>
    <Dokaz_o_uplati_takse>
        <!--Optional:-->
        ${values.dokazOUplatiTakse?.putanjaDoFajla ? `<Putanja_do_fajla>${values.dokazOUplatiTakse.putanjaDoFajla}</Putanja_do_fajla>` : '<Putanja_do_fajla/>'}
        <!--Optional:-->
        <Dostavljeno>${values.dokazOUplatiTakse?.dostavljeno}</Dostavljeno>
    </Dokaz_o_uplati_takse>`
  }

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

  private formatPunomocnik(punomocnik: Punomocnik | undefined) {
    if (punomocnik){
      return punomocnik.lice === "TFizicko_lice" ? this.formatPunomocnikFizickoLice(punomocnik.punomocnik as FizickoLice)
      : this.formatPunomocnikPravnoLice(punomocnik.punomocnik as PravnoLice);
    }
    return ""
  }

  private formatZajednickiPredstavnik(zajednickiPredstavnik: ZajednickiPredstavnik | undefined) {
    if (zajednickiPredstavnik){
      return `<Zajednicki_predstavnik>
      ${this.formatKontaktPodaci(zajednickiPredstavnik.kontaktPodaci)}
      ${this.formatAdresa(zajednickiPredstavnik.adresa)}
      </Zajednicki_predstavnik>`;
    }
    return ""

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
