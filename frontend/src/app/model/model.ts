export enum ZahtevType {
  AUTORSKO_DELO = 'AUTORSKO_DELO',
  PATENT = 'PATENT',
  ZIG = 'ZIG'
}

export enum AD_PrilogType {
  OPIS = 'OPIS',
  PRIMER = 'PRIMER'
}

export enum Z_PrilogType {
  PRIMERAK_ZNAKA = 'PRIMERAK_ZNAKA',
  SPISAK_ROBE_I_USLUGA = 'SPISAK_ROBE_I_USLUGA',
  OPSTI_AKT = "OPSTI_AKT",
  DOKAZ_O_PRAVU_PRVENSTVA = "DOKAZ_O_PRAVU_PRVENSTVA",
  DOKAZ_O_UPLATI_TAKSE = "DOKAZ_O_UPLATI_TAKSE",
  PUNOMOCJE = "PUNOMOCJE",
}
export interface Element{
  Izvestaj: Izvestaj;
}
export interface Izvestaj {
  BrojOdbijenihZahteva: text;
  BrojPodnetihZahteva: text;
  BrojPrihvacenihZahteva: text;
}

export interface text {
  _text: number;
}
