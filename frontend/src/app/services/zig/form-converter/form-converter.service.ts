import {Injectable} from '@angular/core';
import {PodnosilacPrijave, TPrilozi, ZahtevZaPriznanjeZiga} from "../../../model/zig";

@Injectable({
    providedIn: 'root'
})
export class FormConverterService {
    constructor() {
    }


    convertFormToZahtev(form: any, brojPOdnosiociprijave: number, podaciOBrojevima: any, prilozi:TPrilozi): ZahtevZaPriznanjeZiga {
        //const podaciOBrojevima = listOfNumber.reduce((accumulator: number[], num: boolean, index: number) => !num ? [...accumulator, index + 1] : [...accumulator], []);
        const zig = form.value.znak;
        zig.podaciOBrojevimaKlasaRobeIUsluga = podaciOBrojevima;
        const takse = form.value.takse;
        const pravoPrvenstvaIOsnov = form.value.pravoPrvenstvaIosnov.pravoPrvenstvaIOsnov;

        const punomocnikKontaktPodaci = form.value.punomocnikKontaktPodaci;
        const punomocnikAdresa = form.value.punomocnikAdresa;
        const punomocniklice = form.value.punomocnik.lice.lice;
        const punomocnikPravnoLice = form.value.punomocnik.punomocnikPravnoLice;
        const punomocnikFizickoLice = form.value.punomocnik.punomocnikFizickoLice;
        const punomocnikDTO = {
            punomocnik: punomocniklice === "TFizicko_lice" ? {
                adresa: punomocnikAdresa,
                kontaktPodaci: punomocnikKontaktPodaci,
                ime: punomocnikFizickoLice?.ime,
                prezime: punomocnikFizickoLice?.prezime
            } : {
                adresa: punomocnikAdresa,
                kontaktPodaci: punomocnikKontaktPodaci,
                naziv: punomocnikPravnoLice?.nazivPravnoLice
            },
            lice: punomocniklice
        }


        const zajednickiPredstavnikKontaktPodaci = form.value.zajednickiPredstavnikKontaktPodaci;
        const zajednickiPredstavnikAdresa = form.value.zajednickiPredstavnikAdresa;
        const zajednickiPredstavnikDTO = {
            kontaktPodaci: zajednickiPredstavnikKontaktPodaci,
            adresa: zajednickiPredstavnikAdresa
        }
        const podnosilacPrijave: PodnosilacPrijave[] = this.convertPodnosiociPrijave(brojPOdnosiociprijave, form);

        return {
            podnosiociPrijave: podnosilacPrijave,
            punomocnik: punomocnikDTO,
            zajednickiPredstavnik: zajednickiPredstavnikDTO,
            zig: zig,
            pravoPrvenstvaIOsnov: pravoPrvenstvaIOsnov,
            placeneTakse: takse,
            priloziUzZahtev: prilozi
        }
    }

    convertPodnosiociPrijave(brojPodnosiocaPrijave: number, form: any): PodnosilacPrijave[] {
        const podnosiociPrijave: PodnosilacPrijave[] = []
        for (let index = 1; index <= brojPodnosiocaPrijave; index++) {
            const podnosilacLice = form.get(`podnosilacPrijave${index}`).value;
            const podnosilacAdresa = form.get(`podnosilacPrijaveAdresa${index}`).value;
            const podnosilacKontaktPodaci = form.get(`podnosilacPrijaveKontaktPodaci${index}`).value;
            const lice = podnosilacLice.lice.lice;
            const podnosilacPrijave: PodnosilacPrijave = {
                podnosilacPrijave: lice === "TFizicko_lice" ? {
                    adresa: podnosilacAdresa,
                    kontaktPodaci: podnosilacKontaktPodaci,
                    ime: podnosilacLice?.podnosilacPrijaveFizickoLice.ime,
                    prezime: podnosilacLice?.podnosilacPrijaveFizickoLice.prezime
                } : {
                    adresa: podnosilacAdresa,
                    kontaktPodaci: podnosilacKontaktPodaci,
                    naziv: podnosilacLice?.podnosilacPrijavePravnoLice.nazivPravnoLice
                },
                lice: lice
            }
            podnosiociPrijave.push(podnosilacPrijave);
        }
        return podnosiociPrijave;
    }
}
