import { Injectable } from '@angular/core';
import { from } from 'rxjs';
import { Adresa, Autor, AutroskoDelo, FizickoLice, KontaktPodaci, PravnoLice, ZahtevZaAutorskoDelo } from 'src/app/model/autorsko-delo';
import { PrilogImg } from 'src/app/model/model';

@Injectable({
  providedIn: 'root'
})
export class FormConverterService {

  constructor() { }

  convertFormToZahtev(form: any, prilogOpis: PrilogImg, prilogPrimer: PrilogImg, brojAutorPrerada: number, brojAutori: number): ZahtevZaAutorskoDelo{
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

    const opis = prilogOpis.name ? `${prilogOpis.name};custom_separator;${prilogOpis.content}` : undefined
    const primer = prilogPrimer.name ? `${prilogPrimer.name};custom_separator;${prilogPrimer.content}` : undefined
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
      let adresaData = undefined;
      let kontaktData = undefined;
      if(controlName==='Nijepodnosilac'){
        adresaData = form.get(`autori${controlName}Adresa${index}`).value;
        kontaktData = form.get(`autori${controlName}Kontakt${index}`).value;
      }
      
      autori.push({
        ime: fizickoLiceData.ime,
        prezime: fizickoLiceData.prezime,
        drzavljanstvo: fizickoLiceData.drzavljanstvo,

        adresa: adresaData,
        kontaktPodaci: kontaktData,

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
      drzavljanstvo: forms.podnosilacPrijaveAutorLice.drzavljanstvo,

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
      naziv: forms.podnosilacPrijavePravnoLice.nazivPravnoLice
    }
  }

  convertPodnosilacFizickoLice(forms: any): FizickoLice{
    return {
      adresa: forms.podnosilacPrijaveAdresa,
      kontaktPodaci: forms.podnosilacPrijaveKontakt,
      ime: forms.podnosilacPrijaveFizickoLice.ime,
      prezime: forms.podnosilacPrijaveFizickoLice.prezime,
      drzavljanstvo: forms.podnosilacPrijaveFizickoLice.drzavljanstvo
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
