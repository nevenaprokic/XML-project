import { FizickoLice, Adresa, KontaktPodaci, PravnoLice, Status } from "../common/common";



export class ZahtevZaPriznanjePatent{
    constructor(
        public podnosilac: PodnosilacZahteva,
        public pronalazak: Pronalazak,
        public pronalazac: Pronalazac,
        public podaciDostavljanja: Podaci_o_dostavljanju,
        public punomocnik?: Punomcnik,
        public podaciORanijojPrijavi?: Podaci_o_dodatnoj_prijavi,
        public prvenstvo?: ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava,
        public brojPrijave?: string,
        public datumPodnosenja? : Date,
        public priznatiDatumPodnosenja?: Date,
        public idPatenta?: string,
        public primalac?: PrimalazZahteva){}
}
export class PrimalazZahteva {
    constructor(public adresa: Adresa,
        public name: string){}
}

export class Pronalazac extends FizickoLice{
    constructor(public anoniman: boolean,
        public override adresa: Adresa,
        public override kontaktPodaci: KontaktPodaci,
        public override ime: string,
        public override prezime: string,
        public override drzavljanstvo?: string,
        ){
        super(adresa, kontaktPodaci, ime, prezime, drzavljanstvo)
    }
}

export class Pronalazak{
    constructor(public nazivNaSrpskom: string,
        public nazivNaEngleskom: string){

        }
}

export class Podaci_o_dostavljanju{
    constructor(public adresa: Adresa,
        public nacinDostavljanja: NacinDostavljanja){}
}

export class Podaci_o_dodatnoj_prijavi{

    constructor(public tipPrijave: TipDodatnePrijave, 
        public brojPrvobitnePrijave: string,
        public datumPodnosenjaPrvobitnePrijave: Date){}
}

export class RanijaPrijava{
    constructor(public DvoslovnaOznakaDrzave: string,
        public brojPrijave: string,
        public datumPodnosenjaPrijave: string){}
}

export class ZahteZaPriznanjePravaPrvenstvaIzRanijihPrijava{
    constructor(public ranijePrijave: RanijaPrijava[]){}
}

export class Punomcnik{
    constructor(public lice: FizickoLice | PravnoLice,
        public zaZastupanje: boolean,
        public za_prijem_pismena: boolean){}
}

export class PodnosilacZahteva{
    constructor(public lice: FizickoLice | PravnoLice,
        public pronalazac: boolean){}
}

export enum NacinDostavljanja{
    elektronski="elektronski",
    papirna_forma="papirna_forma"
}
export enum TipPRijave{
    dopunska="dopunska",
    izdvojena="izdvojena"
}

export enum TipDodatnePrijave{
    dopunska="dopunska",
    izdvojena="izdvojena"
}
