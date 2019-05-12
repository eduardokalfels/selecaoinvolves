import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alerta } from './alerta';
import { HttpClient, HttpParams } from '@angular/common/http';
import { TipoAlertaEnum } from './tipo-alerta.enum';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {
  
  constructor(private http: HttpClient) { }
  
  private url = '/api/alertas';

  getAlertas(): Observable<Alerta[]> {
    // let params = new HttpParams();
    // params.append('tipoAlerta', tipoAlerta);
    // params.append('pontoDeVenda', pontoDeVenda);
    return this.http.get<Alerta[]>(this.url);
    // return null;
  }
}
