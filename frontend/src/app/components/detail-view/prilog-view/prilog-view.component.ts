import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-prilog-view',
  templateUrl: './prilog-view.component.html',
  styleUrls: ['./prilog-view.component.scss']
})
export class PrilogViewComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data : SafeResourceUrl){}
}
