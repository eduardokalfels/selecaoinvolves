package com.involves.selecao.domain.alerta;

import com.involves.selecao.domain.alerta.tipo.TipoAlertaEnum;

public class AlertaRuptura extends Alerta {
	
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
