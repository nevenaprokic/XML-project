import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
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
export class MetadataSearchComponent implements OnInit,OnChanges {

  @Output()
  queryEvent = new EventEmitter<string>();

  @Input()
  metadataOptions: string[] = []

  operatori: string[] = [
    'AND','OR','NOT'
  ]

  metadataSearchForm: FormGroup;
  
  controlIds: number[] = []
  idCounter = 0

  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });
  maxDate = new Date();
  dateName: string = '';
  constructor() {
    this.metadataSearchForm = new FormGroup({})
    this.addNewQueryForm()
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: any) {
    const metadataOptions: string[] = changes.metadataOptions.currentValue;
    this.metadataOptions = metadataOptions.filter(option=> !option.includes('datum'));
    this.dateName = metadataOptions.filter(option=> option.includes('datum'))[0];
  }

  addNewQueryForm(){
    this.controlIds.push(this.idCounter)
    const newQueryForm= new FormGroup({});
    newQueryForm.addControl(`metadataControl`, new FormControl(''));
    newQueryForm.addControl(`valueControl`, new FormControl(''));
    newQueryForm.addControl(`operatorControl`, new FormControl(''));
    this.metadataSearchForm.addControl( `query${this.idCounter}`, newQueryForm);
    this.idCounter += 1
  }

  search(): void {
    const query: string = this.createQuery();
    this.queryEvent.emit(query);
  }

  private createQuery(): string{
    let queries: Query[] = []

    this.controlIds.forEach((id)=>{
      const metadata = this.getFormGroupById(id).controls["metadataControl"].value
      const operator = this.getFormGroupById(id).controls["operatorControl"].value
      const value = this.getFormGroupById(id).controls["valueControl"].value
      queries.push({operator, metadata, value});
    })

    
    let formatedQuery: string = "";
    queries.forEach((query: Query)=>{
      formatedQuery += `${query.operator ? query.operator + ";": ''}${query.metadata && query.value? (query.metadata + '='+  query.value + ";"): ''}`
    })
    return this.addDateRangeInQuery(formatedQuery);
  }

  addDateRangeInQuery(query: string): string{
    const start: Date | null = this.range.controls.start.value;
    const end: Date | null = this.range.controls.end.value;
    const startDateQuery = `${this.dateName}>="${start?.toISOString().replace('Z', '+01:00')}"`
    const endDateQuery = `;AND;${this.dateName}<="${end?.toISOString().replace('Z', '+01:00')}"`
    return query + `${query!=''? 'AND;' : ''}`+ startDateQuery + endDateQuery;
  }

  operatorSelectChange(event: any, id: number): void {
    if(!event.value){
      this.getFormGroupById(id).controls["metadataControl"].setValue('')
      this.getFormGroupById(id).controls["valueControl"].setValue('')
    }
  }

  metadataSelectChange(event: any, id: number): void {
    if(!event.value){
      this.getFormGroupById(id).controls["valueControl"].setValue('')
      this.getFormGroupById(id).controls["operatorControl"].setValue('')
    }
  }

  addNewInputField(): void {
    this.addNewQueryForm();
  }

  removeInputField(id: number): void {
    const index = this.controlIds.indexOf(id);
    this.controlIds.splice(index, 1);
    this.metadataSearchForm.removeControl(`query${id}`)
  }

  getFormGroupById(id: number): FormGroup {
    return this.metadataSearchForm.controls[`query${id}`] as FormGroup
  }
}
