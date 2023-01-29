export class Adresa {
    constructor (public broj: string, 
        public ulica: string, public grad: string, public drzava?: string, public postanskiBroj? : string){}
  }
  
  export class KontaktPodaci {
    constructor( public faks: string,
        public email: string, 
        public telefon: string,
        ){}
  }
  
  export class FizickoLice {
    constructor(public adresa: Adresa,
      public kontaktPodaci: KontaktPodaci,
      public ime: string,
      public prezime: string,
      public drzavljanstvo?: string){}
  }
  
  export class PravnoLice {
    constructor(public adresa: Adresa,
        public kontaktPodaci: KontaktPodaci,
        public naziv: string){}
}

export enum Status{
  NEOBRADJEN = "neobradjen",
  ODBIJEN ="odbijen",
  ODOBREN="odobren"
}
