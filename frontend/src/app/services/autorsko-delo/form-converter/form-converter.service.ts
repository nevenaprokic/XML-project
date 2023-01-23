import { Injectable } from '@angular/core';
import { from } from 'rxjs';
import { Adresa, Autor, AutroskoDelo, FizickoLice, KontaktPodaci, PravnoLice, ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';

@Injectable({
  providedIn: 'root'
})
export class FormConverterService {

  constructor() { }

  convertFormToZahtev(form: any, opis: any, primer: any, brojAutorPrerada: number, brojAutori: number): ZahtevZaAutorskoDelo{
    const podaciOAutorskomDeluForm = form.value.podaciOAutorskomDelu
    
    const podnosilacPrijaveFizickoLice = form.value.podnosilacPrijaveFizickoLice;
    const podnosilacPrijavePravnoLice = form.value.podnosilacPrijavePravnoLice;
    const podnosilacPrijaveAutorLice = form.value.podnosilacPrijaveAutorLice;
    const podnosilacPrijaveAutorObelezja = form.value.podnosilacPrijaveAutorObelezja;
    const podnosilacPrijaveAdresa = form.value.podnosilacPrijaveAdresa
    const podnosilacPrijaveKontakt = form.value.podnosilacPrijaveKontakt

    const podnosilacAutorDTO = {podnosilacPrijaveAutorLice, podnosilacPrijaveAutorObelezja, podnosilacPrijaveAdresa, podnosilacPrijaveKontakt}
    const podnosilacPravnoLiceDTO = {podnosilacPrijavePravnoLice, podnosilacPrijaveAdresa, podnosilacPrijaveKontakt}
    const podnosilacFizickoLiceDTO = {podnosilacPrijaveFizickoLice, podnosilacPrijaveAdresa, podnosilacPrijaveKontakt}
    
    const autoriDela = this.convertAutoriDela(brojAutori, form, 'Nijepodnosilac');
    const autoriOriginala = this.convertAutoriDela(brojAutorPrerada, form, 'Prerade');
    return {
      podnosilacAutor: podnosilacPrijaveAutorLice ? this.convertPodnosilacAutor(podnosilacAutorDTO) : undefined,
      podnosilacPravnoLice: podnosilacPrijavePravnoLice ? this.convertPodnosilacPravnoLice(podnosilacPravnoLiceDTO) : undefined,
      podnosilacFizickoLice: podnosilacPrijaveFizickoLice ? this.convertPodnosilacFizickoLice(podnosilacFizickoLiceDTO) : undefined,
      autorskoDelo: this.convertAutorskoDelo(podaciOAutorskomDeluForm, autoriDela, autoriOriginala),
      opis, primer
    }
  }
  convertAutoriDela(brojAutora: number, form: any, controlName: string): Autor[] {
    // autoriNijepodnosilacAutor + brojAutori
    // autoriPreradeAutor + brojAutorPrerada
    const autori: Autor[] = []
    for (let index = 1; index <= brojAutora; index++) {
      const autorData = form.get(`autori${controlName}Autor${index}`).value;
      const fizickoLiceData = form.get(`autori${controlName}FizickoLice${index}`).value;
      autori.push({
        ime: fizickoLiceData.ime,
        prezime: fizickoLiceData.ime,
        drzavljanstvo: 'DRZAVLJANSTVO',

        adresa: undefined,
        kontaktPodaci: undefined,

        anonimni: autorData.anonimni,
        godinaSmrti: autorData.godinaSmrti,
        primarni: autorData.primarni,
        pseudonim: autorData.pseudonim
      })
    }
    return autori;
  }

  convertPodnosilacAutor(forms: any): Autor{
    return {
      adresa: forms.podnosilacPrijaveAdresa,
      kontaktPodaci: forms.podnosilacPrijaveKontakt,
      ime: forms.podnosilacPrijaveAutorLice.ime,
      prezime: forms.podnosilacPrijaveAutorLice.prezime,
      drzavljanstvo: 'Српско',
      anonimni: forms.podnosilacPrijaveAutorObelezja.anonimni,
      primarni: forms.podnosilacPrijaveAutorObelezja.primarni,
      godinaSmrti: forms.podnosilacPrijaveAutorObelezja.godinaSmrti,
      pseudonim: forms.podnosilacPrijaveAutorObelezja.pseudonim
    }
  }

  convertPodnosilacPravnoLice(forms: any): PravnoLice{
    return {
      adresa: forms.podnosilacPrijaveAdresa,
      kontaktPodaci: forms.podnosilacPrijaveKontakt,
      naziv: forms.podnosilacPrijavePravnoLice.naziv
    }
  }

  convertPodnosilacFizickoLice(forms: any): FizickoLice{
    return {
      adresa: forms.podnosilacPrijaveAdresa,
      kontaktPodaci: forms.podnosilacPrijaveKontakt,
      ime: forms.podnosilacPrijaveFizickoLice.ime,
      prezime: forms.podnosilacPrijaveFizickoLice.prezime,
      drzavljanstvo: 'Српско'
    }
  }

  convertAutorskoDelo(podaciOAutorskomDeluForm: any, autoriDela:Autor[], autoriOriginala: Autor[]) : AutroskoDelo{
    return {
      autori: autoriDela,
      formaZapisa: podaciOAutorskomDeluForm.formaZapisaAutorskogDela,
      identifikator: {
        alternativniNaslov: podaciOAutorskomDeluForm.alternativniNaslovAutorskogDela,
        naslov: podaciOAutorskomDeluForm.naslovAutorskogDela
      },
      podaciOOriginalu:{
        autori: autoriOriginala,
        identifikator: {
          alternativniNaslov: '',
          naslov: podaciOAutorskomDeluForm.naslovPreradeAutorskogDela
        }
      },
      nacinKoriscenja: podaciOAutorskomDeluForm.nacinKoriscenja,
      prerada: podaciOAutorskomDeluForm.naslovPreradeAutorskogDela==='',
      radniOdnos: podaciOAutorskomDeluForm.podaciORadnomOdnosu,
      vrsta: podaciOAutorskomDeluForm.vrstaAutorskogDela
    }
  }
}
