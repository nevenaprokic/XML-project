import { Status } from "./common/common";

export enum AD_PrilogType {
    OPIS='OPIS',
    PRIMER='PRIMER'
}


export interface Adresa {
  broj: string;
  ulica: string;
  grad: string;
}

export interface KontaktPodaci {
  faks: string;
  email: string;
  telefon: string;
}

export interface FizickoLice {
    adresa: Adresa;
    kontaktPodaci: KontaktPodaci;
    ime: string;
    prezime: string;
    drzavljanstvo?: string;
}

export interface Autor {
    anonimni : boolean | undefined
    godinaSmrti: number | undefined
    primarni: boolean |undefined
    pseudonim?: string;

    adresa: Adresa | undefined
    kontaktPodaci: KontaktPodaci | undefined
    ime: string;
    prezime: string;
    drzavljanstvo?: string;
}

export interface PravnoLice {
    adresa: Adresa;
    kontaktPodaci: KontaktPodaci;
    naziv: string;
}

export interface Identifikator {
    naslov: string;
    alternativniNaslov: string;
}

export interface PodaciOOriginalu {
    autori: Autor[]
    identifikator: Identifikator
}

export interface AutroskoDelo extends PodaciOOriginalu{
    radniOdnos: string;
    nacinKoriscenja: string;
    vrsta: VrstaAutorskogDela;
    formaZapisa: FormaZapisaAutorskogDela;
    prerada: boolean;
    podaciOOriginalu: PodaciOOriginalu | undefined 
}

export enum FormaZapisaAutorskogDela {
    STAMPARSKI_TEKST="stamparski tekst",
    OPTICKI_DISK="opticki disk",
    OSTALO="ostalo"
}

export enum VrstaAutorskogDela {
    KNJIZEVNO_DELO="knjizevno delo",
    MUZICKO_DELO="muzicko delo",
    LIKOVNO_DELO="likovno delo",
    RACUNARSKI_PROGRAM="racunarski program"
}

export interface ZahtevZaAutorskoDelo{ 
    podnosilacAutor: Autor | undefined; 
    podnosilacPravnoLice: PravnoLice | undefined; 
    podnosilacFizickoLice: FizickoLice | undefined; 
    autorskoDelo: AutroskoDelo; 
    opis: any; 
    primer: any; 
    status?: Status;
    naslov?: string | undefined
    brojPrijave?: string | undefined
    datumPodnosenja?: Date | undefined,
    id?: string;
    podnosilacNaziv? : string
}