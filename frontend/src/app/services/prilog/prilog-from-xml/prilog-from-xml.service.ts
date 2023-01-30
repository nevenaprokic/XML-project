import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { PrilogViewComponent } from 'src/app/components/detail-view/prilog-view/prilog-view.component';
import { AutorskoDeloService } from '../../autorsko-delo/autorsko-delo.service';
import { saveAs } from 'file-saver';
import * as FileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class PrilogFromXmlService {

  constructor(private dialog: MatDialog, private sanitizer: DomSanitizer, private autorskoDeloService: AutorskoDeloService) { }

  convertToImage(xml:any){
    const convert = require('xml-js');
    const prilog: any = JSON.parse(convert.xml2json(xml, {compact: true, spaces: 4}));
    const content = prilog["Prilog_Image"]["Sadrzaj_priloga"]._text
    console.log(content)
    const image : SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${content}`);
    this.dialog.open(PrilogViewComponent, {
      data: image,
    });
  }

  preuzmi(zahtevId: string, prilogName: string){
      this.autorskoDeloService.getPrilog(zahtevId, prilogName).subscribe({
        next: (response) => {
          const convert = require('xml-js');
          const prilog: any = JSON.parse(convert.xml2json(response, {compact: true, spaces: 4}));
          const content = prilog["Prilog_Image"]["Sadrzaj_priloga"]._text
          const myFile = this.base64ToFile(content, "slika" ,'image/png');
          FileSaver.saveAs(myFile, "slika");
          
        },
        error: (error) => {
          console.log(error)
        }
      })
    }
  
    base64ToFile(base64Data : any, tempfilename: any, contentType: any) {
      contentType = contentType || '';
      const sliceSize = 1024;
      const byteCharacters = atob(base64Data);
      const bytesLength = byteCharacters.length;
      const slicesCount = Math.ceil(bytesLength / sliceSize);
      const byteArrays = new Array(slicesCount);
  
      for (let sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
        const begin = sliceIndex * sliceSize;
        const end = Math.min(begin + sliceSize, bytesLength);
  
        const bytes = new Array(end - begin);
        for (let offset = begin, i = 0; offset < end; ++i, ++offset) {
          bytes[i] = byteCharacters[offset].charCodeAt(0);
        }
        byteArrays[sliceIndex] = new Uint8Array(bytes);
      }
      return new File(byteArrays, tempfilename, {type: contentType});
    }
}
