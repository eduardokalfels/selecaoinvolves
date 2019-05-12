package com.involves.selecao.domain.alerta;

import com.involves.selecao.domain.alerta.tipo.TipoAlertaEnum;

public abstract class Alerta {
	
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
//	public Integer getFlTipo() {
//		return flTipo;
//	}
//	public void setFlTipo(Integer flTipo) {
//		this.flTipo = flTipo;
//	}
	
	public abstract TipoAlertaEnum getTipo();
}
