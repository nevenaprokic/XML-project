import { Component, Input } from '@angular/core';
import { TTakse } from 'src/app/model/zig';

@Component({
  selector: 'app-takse-detail-view',
  templateUrl: './takse-detail-view.component.html',
  styleUrls: ['./takse-detail-view.component.scss']
})
export class TakseDetailViewComponent {

  @Input() takse! : TTakse;
}
