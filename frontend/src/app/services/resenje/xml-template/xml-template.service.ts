import {Injectable} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class XmlTemplateService {

  constructor() {
  }

  createNewXML(values: FormGroup, status: any, idZahteva: string) {
    const date = new Date();
    const dateForXML = date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
    const sluzbenikIme = "PERA";
    const sluzbenikPrezime = "Peric";
    return `<?xml version="1.0" encoding="UTF-8"?>
            <Resenje datum_resenja="${dateForXML}" status="${status === 'TOdobren' ? 'odobren': 'odbijen'}"
                     xmlns="http://ftn.uns.ac.rs/resenje"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://ftn.uns.ac.rs/resenje  ./resenje.xsd"
                     xmlns:zig="http://ftn.uns.ac.rs/zig">
                <zig:idZiga>
                    <zig:idZ>${idZahteva}</zig:idZ>
                </zig:idZiga>
                ${this.convertDodatak(status, values)}
                <Sluzbenik>
                    <Ime>${sluzbenikIme}</Ime>
                    <Prezime>${sluzbenikPrezime}</Prezime>
                </Sluzbenik>
            </Resenje>`;
  }

  convertDodatak(status: any, values: FormGroup) {
    if (status === "TOdobren") {
      return `<Dodatak xsi:type="TOdobren">
                    <Sifra>${values.value.sifra}</Sifra>
                </Dodatak>`;
    }
    return `<Dodatak xsi:type="TOdbijen">
                    <Obrazlozenje>${values.value.obrazlozenje}</Obrazlozenje>
                </Dodatak>`;
  }
}
