import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alerta } from './alerta';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {
  
  constructor(private http: HttpClient) { }
  
  private url = '/api/alertas';

  getAlertas(): Observable<Alerta[]> {
    return this.http.get<Alerta[]>(this.url);
    // return null;
  }
}
