import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MembreModel} from '../membre-model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MembreserviceService {

  private membresUrl: string;

  constructor(private http: HttpClient) {
    this.membresUrl = 'http://localhost:8080/membres';
  }

  public findAll(): Observable<MembreModel[]> {
    return this.http.get<MembreModel[]>(this.membresUrl);
  }
}
