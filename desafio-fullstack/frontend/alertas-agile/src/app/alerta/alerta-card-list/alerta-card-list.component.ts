import { Component, OnInit } from '@angular/core';
import { Alerta } from '../alerta';
import { AlertaService } from '../alerta.service';
import { TipoAlertaEnum } from '../tipo-alerta.enum';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-alerta-card-list',
  templateUrl: './alerta-card-list.component.html',
  styleUrls: ['./alerta-card-list.component.scss']
})
export class AlertaCardListComponent implements OnInit {

  form: FormGroup;

  // tipoAlertaSelecionado: TipoAlertaEnum;

  tiposAlerta: TipoAlertaEnum[];

  alertas: Alerta[];

  constructor(
    private alertaService: AlertaService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.configurarFormulario();
    this.tiposAlerta = [ TipoAlertaEnum.RUPTURA, TipoAlertaEnum.PARTICIPACAO, TipoAlertaEnum.PRECO ];
    this.carregarAlertas();
  }
  
  carregarAlertas() {
    this.alertaService.getAlertas()
    .subscribe(alertas => {
      this.alertas = alertas.filter(alerta => {
        let tipoAlerta = this.form.value.tipoAlerta;
        let matchesTipo = !tipoAlerta || alerta.tipo === tipoAlerta;
        
        let pontoDeVenda = this.form.value.pontoDeVenda;
        let matchesPontoDeVenda = !pontoDeVenda || alerta.pontoDeVenda.toLowerCase().match(new RegExp(pontoDeVenda.toLowerCase(), 'g'));
        return matchesTipo && matchesPontoDeVenda;
      })
      // this.alertas = alertas;
    });
  }

  limpar() {
    this.form.reset();
    this.carregarAlertas();
  }
  
  getDescricaoTipoAlerta(tipoAlerta: TipoAlertaEnum) {
    if(tipoAlerta === TipoAlertaEnum.RUPTURA) {
      return "Ruptura"; 
    } else if(tipoAlerta === TipoAlertaEnum.PRECO) {
      return "Preço"; 
    } else if(tipoAlerta === TipoAlertaEnum.PARTICIPACAO) {
      return "Participação"; 
    }
  }

  getIconeTipoAlerta(alerta: Alerta): string {
    if(alerta.tipo === TipoAlertaEnum.RUPTURA) {
      return 'fa-unlink';
    } else if(alerta.tipo === TipoAlertaEnum.PRECO) {
      return 'fa-dollar-sign';
    } else {
      return 'fa-percentage';
    }
  }

  private configurarFormulario() {
		this.form = this.formBuilder.group({
			tipoAlerta: [null],
			pontoDeVenda: ['']
		});
	}
}
