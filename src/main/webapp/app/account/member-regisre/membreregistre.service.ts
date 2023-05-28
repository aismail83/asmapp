import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Membreregistre } from './membreregistre';
@Injectable({
  providedIn: 'root'
})
export class MembreregistreService {
  private membresUrl = ('http://localhost:8080/api/regisre');

  constructor(private http: HttpClient) {    
}

  save(Membreregistre: Membreregistre): Observable<{}> {
  return this.http.post(this.membresUrl,Membreregistre);
  }
}
