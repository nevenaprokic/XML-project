import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user/user';
import { UserToXmlService } from 'src/app/services/user/converter/to-xml/user-to-xml.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  name_regexp: string = "^[a-zA-Z]+$";
  hidePassword: boolean = true;
  hideRepeatPassword: boolean = true

  registerForm = new FormGroup({
    email: new FormControl('', [Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    repeatPassword: new FormControl('', [Validators.required, Validators.minLength(8)]),
    firstName: new FormControl('',[Validators.required, Validators.pattern(this.name_regexp)]),
    lastName: new FormControl('',[Validators.required, Validators.pattern(this.name_regexp)]),
  });

  ngOnInit(): void {
    
  }

  constructor(private userService: UserService, private userToXML : UserToXmlService, private snackbar: MatSnackBar, private router: Router) {
    
  }

  validatePassword(): boolean{
    if(this.registerForm.value.password !== this.registerForm.value.repeatPassword){
      this.openMessage("Lozinka i ponovljena lozinka moraju biti iste.", "OK");
      return false;
    }return true;
  }

  onSubmit(){
    if(this.validatePassword()){
      let userXML = this.userToXML.convertusertoXML(this.registerForm.value)
      this.userService.registeruser(userXML).subscribe({
        next: (response) =>{
          this.router.navigateByUrl("/login")
        },
        error : (error) =>{
          console.log(error)
          this.openMessage(error.error, "GREŠKA")
        }
  
      })
    }
  }

  openMessage(message: string, action: string) {
    this.snackbar.open(message, action,
    {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: 3000, //fali klasa za boju
      });
  }
}
