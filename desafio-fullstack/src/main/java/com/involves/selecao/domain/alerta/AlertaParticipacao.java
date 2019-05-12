package com.involves.selecao.domain.alerta;

import com.involves.selecao.domain.alerta.tipo.TipoAlertaEnum;

public class AlertaParticipacao extends Alerta {
	
	private String categoria;
	private Integer margem;
	
	public Integer getMargem(){
		return margem;
	}
	public void setMargem(Integer margem){
		this.margem = margem;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public TipoAlertaEnum getTipo() {
		return TipoAlertaEnum.PARTICIPACAO;
	}
}
