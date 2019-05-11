import { Component, OnInit } from '@angular/core';
import { Alerta } from '../alerta';
import { AlertaService } from '../alerta.service';

@Component({
  selector: 'app-alerta-card-list',
  templateUrl: './alerta-card-list.component.html',
  styleUrls: ['./alerta-card-list.component.scss']
})
export class AlertaCardListComponent implements OnInit {

  alertas: Alerta[];

  constructor(private alertaService: AlertaService) { }

  ngOnInit() {
    this.carregarAlertas();
  }


  private carregarAlertas() {
    this.alertaService.getAlertas()
      .subscribe(alertas => {
        this.alertas = alertas;
      });
  }
}
