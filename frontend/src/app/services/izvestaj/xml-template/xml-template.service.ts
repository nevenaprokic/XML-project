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
        const startDateForXML = `${dateForm.value.startDate?.getFullYear()}-${dateForm.value.startDate?.getMonth()+1}-${dateForm.value.startDate?.getDate()}`;
        // @ts-ignore
        const endDateForXML = `${dateForm.value.endDate?.getFullYear()}-${dateForm.value.endDate?.getMonth()+1}-${dateForm.value.endDate?.getDate()}`;
        return `<?xml version="1.0" encoding="UTF-8"?>
        <Izvestaj pocetni_datum_izvestaja="${startDateForXML}" krajnji_datum_izvestaja="${endDateForXML}"
             xmlns="http://ftn.uns.ac.rs/izvestaj"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://ftn.uns.ac.rs/izvestaj  ./izvestaj.xsd">
        </Izvestaj> `;
    }
}
