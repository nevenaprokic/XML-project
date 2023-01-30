import { Component, Input } from '@angular/core';
import { TZig, } from 'src/app/model/zig';


@Component({
  selector: 'app-znak-ziga-detail-view',
  templateUrl: './znak-ziga-detail-view.component.html',
  styleUrls: ['./znak-ziga-detail-view.component.scss']
})
export class ZnakZigaDetailViewComponent {


  @Input() zig! : TZig;

  counter(num: number) {
    return new Array(num);
}
}
