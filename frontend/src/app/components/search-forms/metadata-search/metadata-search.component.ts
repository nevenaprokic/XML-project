import { Component, EventEmitter, OnInit, Output,  } from '@angular/core';
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

  @Output()
  searchResult = new EventEmitter<any>();

  metadataOptions: string[] = [
    'datum_podnosenja', 
    'autorsko_delo', 
    'primarni_autor',
    'koautor',
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
        this.searchResult.emit(res);
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
