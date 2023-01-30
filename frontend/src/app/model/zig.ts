import { Status } from "./common/common";

export interface ZahtevZaPriznanjeZiga {
    podnosiociPrijave: PodnosilacPrijave[];
    punomocnik?: Punomocnik;
    zajednickiPredstavnik?: ZajednickiPredstavnik;
    zig: TZig;
    pravoPrvenstvaIOsnov: string;
    placeneTakse: TTakse;
    priloziUzZahtev: TPrilozi;
    id? : string
    datumPodnosenjaZahteva?: Date;
    brojZahteva? : string;
    status?: Status
    podnosiociStr? :string
}

export interface PodnosilacPrijave {
    podnosilacPrijave: FizickoLice | PravnoLice;
    lice?:string;
}

export interface Punomocnik {
    lice?: string;
    punomocnik: FizickoLice | PravnoLice;
}

export interface TPrilozi {
    primerakZnaka: TDopuna;
    spisakRobeIUsluga: TDopuna
    punomocje: TDopuna;
    punomocjeRanijePrilozeno: TDopuna;
    punomocjeNaknadnoDostavljeno: TDopuna;
    opstiAktOKolektivnomZiguGarancije: TDopuna;
    dokazOPravuPrvenstva: TDopuna;
    dokazOUplatiTakse: TDopuna;
}

export interface TDopuna {
    putanjaDoFajla: string;
    dostavljeno: boolean;
}

export interface TTakse {
    osnovnaTaksa: number;
    zaGraficko: number;
    zaKlasu: number;
    ukupno? : number
}

export interface TZig {
    vrstaNaOsnovuKorisnika: VrstaZigaNaOsnovuKorisnika;
    vrstaNaOsnovuIzgleda: VrstaZigaNaOsnovuIzgleda;
    izgledZnaka: string;
    bojaZnaka: string;
    transliteracijaZnaka: string;
    vrstaNaOsnovuIzgledaStr?: string;
    prevodZnaka: string;
    opisZnaka: string;
    podaciOBrojevimaKlasaRobeIUsluga: number[];
}

export enum VrstaZigaNaOsnovuIzgleda {
    VERBALNI_ZIG = "verbalni zig",
    GRAFICKI = "graficki",
    KOMBINOVANI = "kombinovani",
    TRODIMENZIONALNI = "trodimenzionalni",
    DRUGO = 'drugo'
}

export enum VrstaZigaNaOsnovuKorisnika {
    INDIVIDUALNI_ZIG = "individualni zig",
    KOLEKTIVNI_ZIG = "kolektivni zig",
    ZIG_GENERACIJE = "zig generacije"
}

export interface ZajednickiPredstavnik {
    kontaktPodaci: KontaktPodaci;
    adresa: Adresa;
}

export interface PravnoLice {
    adresa: Adresa;
    kontaktPodaci: KontaktPodaci;
    naziv: string;
}

export interface Adresa {
    broj: string;
    ulica: string;
    grad: string;
    drzava?:string;
    postanskiBroj?:string;
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