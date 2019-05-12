package com.involves.selecao.domain.alerta.dto;

import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;

public class AlertaRupturaDTO extends AlertaDTO {
	
	private String produto;
	
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}

	@Override
	public TipoAlertaEnum getTipo() {
		return TipoAlertaEnum.RUPTURA;
	}
}
