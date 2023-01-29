import { Component, Output, EventEmitter } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipEditedEvent, MatChipInputEvent} from '@angular/material/chips';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';

@Component({
  selector: 'app-text-search',
  templateUrl: './text-search.component.html',
  styleUrls: ['./text-search.component.scss']
})
export class TextSearchComponent {

  @Output()
  searchResult = new EventEmitter<any>();

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  keywords: string[] = [];

  constructor(
    private autorskoDeloService: AutorskoDeloService
  ) {}
  
  search(): void {
    const query: string = this.createQuery();
    console.log(query)
    this.autorskoDeloService.searchText(query).subscribe({
      next: (res: any) => {
        this.searchResult.emit(res);
      },
      error: (res: any) => {
        console.log(res)
      }
    })
  }
  private createQuery(): string{
    return this.keywords.join(";");
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      this.keywords.push(value);
    }
    event.chipInput!.clear();
  }

  remove(keyword: string): void {
    const index = this.keywords.indexOf(keyword);
    if (index >= 0) {
      this.keywords.splice(index, 1);
    }
  }

  edit(keyword: string, event: MatChipEditedEvent) {
    const value = event.value.trim();
    if (!value) {
      this.remove(keyword);
      return;
    }
    const index = this.keywords.indexOf(keyword);
    if (index >= 0) {
      this.keywords[index] = value;
    }
  }
}
