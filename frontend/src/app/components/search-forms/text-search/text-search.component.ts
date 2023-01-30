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
  queryEvent = new EventEmitter<string>();

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  keywords: string[] = [];

  constructor(
  ) {}
  
  search(): void {
    const query: string = this.createQuery();
    this.queryEvent.emit(query);
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
