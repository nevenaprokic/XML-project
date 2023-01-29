import { Component, Input, OnInit } from '@angular/core';
import { PravnoLice } from 'src/app/model/common/common';

@Component({
  selector: 'app-pravno-lice-detail-view',
  templateUrl: './pravno-lice-detail-view.component.html',
  styleUrls: ['./pravno-lice-detail-view.component.scss']
})
export class PravnoLiceDetailViewComponent implements OnInit{
  
  @Input() lice! : PravnoLice;

  ngOnInit(): void {
  }

}
