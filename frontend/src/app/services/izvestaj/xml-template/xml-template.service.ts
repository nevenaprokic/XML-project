import {Injectable} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class XmlTemplateService {

  constructor() {
  }

  createNewXML(dateForm: FormGroup<{ endDate: FormControl<Date | null>; startDate: FormControl<Date | null> }>) {
    // @ts-ignore
    const startDateForXML = this.convertDate(dateForm.value.startDate);
    // @ts-ignore
    const endDateForXML = this.convertDate(dateForm.value.endDate);
    return `<?xml version="1.0" encoding="UTF-8"?>
        <Izvestaj pocetni_datum_izvestaja="${startDateForXML}" krajnji_datum_izvestaja="${endDateForXML}"
             xmlns="http://ftn.uns.ac.rs/izvestaj"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://ftn.uns.ac.rs/izvestaj  ./izvestaj.xsd">
        </Izvestaj> `;
  }

  convertDate(date: Date) {
    return date.getFullYear()+ "-" +  ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
  }
}
