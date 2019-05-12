package com.involves.selecao.domain.alerta.dto;

import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;

public class AlertaParticipacaoDTO extends AlertaDTO {
	
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
