import { Component, OnInit } from '@angular/core';
import { AutroskoDelo } from 'src/app/model/autorsko-delo';
import { AutorskoDeloService } from 'src/app/services/autorsko-delo/autorsko-delo.service';
import { UserService } from 'src/app/services/user/user.service';
import { ZigService } from 'src/app/services/zig/zig.service';

@Component({
  selector: 'app-autorsko-delo-table-view',
  templateUrl: './autorsko-delo-table-view.component.html',
  styleUrls: ['./autorsko-delo-table-view.component.scss']
})
export class AutorskoDeloTableViewComponent implements OnInit{
  
  constructor(private userService : UserService, private autorskoDeloService: AutorskoDeloService, private zig: ZigService){}

  ngOnInit(): void {
    if (this.userService.getRoleCurrentUserRole() === "SLUZBENIK"){
    //   this.autorskoDeloService.getAll().subscribe({
    //     next: (response) => {
    //       console.log(response)
    //     },
    //     error: (error) => {
    //       console.log(error)
    //     }
    //   })
     this.zig.getAll().subscribe({
        next: (response) => {
          console.log(response)
        },
        error: (error) => {
          console.log(error)
        }
      })
    
  }}

}
