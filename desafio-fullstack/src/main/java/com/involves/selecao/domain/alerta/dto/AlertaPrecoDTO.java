package com.involves.selecao.domain.alerta.dto;

import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;

public class AlertaPrecoDTO extends AlertaDTO {
	
	private String produto;
	private Integer margem;
	
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Integer getMargem(){
		return margem;
	}
	public void setMargem(Integer margem){
		this.margem = margem;
	}
	@Override
	public TipoAlertaEnum getTipo() {
		return TipoAlertaEnum.PRECO;
	}
}
