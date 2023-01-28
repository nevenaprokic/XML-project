import { Component, OnInit } from '@angular/core';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';

interface Query {
  operator?: string,
  metadata: string,
  value: string
}


@Component({
  selector: 'app-metadata-search',
  templateUrl: './metadata-search.component.html',
  styleUrls: ['./metadata-search.component.scss']
})
export class MetadataSearchComponent implements OnInit {

  
	// public static final String ZAHTEV_ZA_AUTORSKO_DELO = "zahtevZaAutorskoDelo";
	// public static final String DATUM_PODNOSENJA =  "datum_podnosenja";
	// public static final String AUTORSKO_DELO =  "autorsko_delo";
	// public static final String PRIMARNI_AUTOR =  "primarni_autor";
	// public static final String KOAUTOR =  "koautor";
	// public static final String PODNOSILAC =  "podnosilac";
	// public static final String IME_PRODNOSIOCA =  "ime_prodnosioca";
  metadataOptions: string[] = [
    'datum_podnosenja', 
    'autorsko_delo', 
    'primarni_autor',
    'koautor',
    'podnosilac',
    'ime_prodnosioca'
  ]

  operatori: string[] = [
    'AND','OR','NOT'
  ]

  queries: Query[] = [{metadata:'', value: ''}]
  inputFiledsCount: number = 1;

  constructor(private autorskoDeloService: AutorskoDeloService) {}

  ngOnInit(): void {
  }

  search(): void {
    const query: string = this.createQuery();
    this.autorskoDeloService.searchMetadata(query).subscribe({
      next: (res: any) => {
        console.log(res)
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }
  private createQuery(): string{
    let formatedQuery: string = "";
    this.queries.forEach((query: Query)=>{
      formatedQuery += `${query.operator ? query.operator + ";": ''}${query.metadata}=${query.value};`
    })
    return formatedQuery;
  }

  operatorSelectChange(event: any, index: number): void {
    this.queries[index].operator = event.value;
  }

  metadataSelectChange(event: any, index: number): void {
    this.queries[index].metadata = event.value;
  }

  inputValueDone($event: any, index: number): void { 
    this.queries[index].value = $event.target.value;
  }

  addNewInputField(): void {
    this.inputFiledsCount += 1;
    this.queries.push({operator:'', metadata: '', value: ''})
  }

  removeInputField(index: number): void {
    this.inputFiledsCount -= 1
    this.queries.splice(index, 1);
  }

  arrayFromNumber(): Array<number> {
    return [...Array(this.inputFiledsCount).keys()]
  }
}
