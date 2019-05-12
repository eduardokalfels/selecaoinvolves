package com.involves.selecao.domain.alerta.dto;

import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;

public abstract class AlertaDTO {
	
	private String pontoDeVenda;
	private String descricao;
	
	public String getPontoDeVenda() {
		return pontoDeVenda;
	}
	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public abstract TipoAlertaEnum getTipo();
}
