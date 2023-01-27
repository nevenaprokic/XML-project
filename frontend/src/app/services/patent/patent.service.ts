import { Injectable } from '@angular/core';
import { environment } from 'src/app/environments/environment';
import { HttpClientService } from '../custom-http/http-client.service';

@Injectable({
  providedIn: 'root'
})
export class PatentService {

  constructor(private http: HttpClientService) { }

  getAll(){
      return this.http.get(environment.PATENT_BASE_URL + "/patent/all-patents")
  }
}
