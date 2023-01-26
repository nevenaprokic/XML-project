import { Component, Inject, OnInit } from '@angular/core';
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.scss']
})
export class ErrorMessageComponent implements OnInit{

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any, private router : Router) {
  }

  ngOnInit(): void {
    console.log("tuu")
  }

  onClick() {
    this.data.preClose();
    this.router.navigateByUrl('/passenger');
  }

}
