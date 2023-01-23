
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
    anonimni?: boolean;
    godinaSmrti?: number;
    primarni?: boolean;
    pseudonim?: string;

    adresa?: Adresa;
    kontaktPodaci?: KontaktPodaci;
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
    podaciOOriginalu: PodaciOOriginalu 
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
}