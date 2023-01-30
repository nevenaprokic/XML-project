import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileUtilService {

  constructor() { }

  downloadDocument(content: any, fileName: string){
    const binaryData = [];
    binaryData.push(content);
    const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/xml'}));
    const a = document.createElement('a');
    document.body.appendChild(a);
    a.setAttribute('style', 'display: none');
    a.setAttribute('target', 'blank');
    a.href = url;
    a.download = fileName;
    a.click();
    window.URL.revokeObjectURL(url);
    a.remove();
  }

}
