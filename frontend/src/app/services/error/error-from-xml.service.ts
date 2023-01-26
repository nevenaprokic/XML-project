import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class ErrorFromXmlService {

  constructor(private snackBar: MatSnackBar) { }

  getErrorFromXML(xml: any){
    console.log(xml);
    const convert = require('xml-js');
    const errorToken = JSON.parse(convert.xml2json(xml, {compact: true, spaces: 4}));
    const error = errorToken.ErrorMessage;
    console.log(error);
    return new Error( error.message._text, error.status._text);
  }

  
  showNotificationComponent(message: string, component: any) {
      this.snackBar.openFromComponent(component, {
        data: {
          message: message,
          preClose: () => {
            this.snackBar.dismiss()
          }
        },
        verticalPosition: 'top',
        horizontalPosition: 'center',
      });
    }
}
