package com.involves.selecao.domain.alerta.dto;

import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;
import com.involves.selecao.domain.alerta.exceptions.TipoAlertaInvalidoException;

public class AlertaFactory {
	
	public static AlertaDTO getAlerta(String tipoAlerta) {
		return AlertaFactory.getAlerta(TipoAlertaEnum.valueOf(tipoAlerta));
	}
	
	public static AlertaDTO getAlerta(TipoAlertaEnum tipoAlertaEnum) {
		switch (tipoAlertaEnum) {
		case RUPTURA:
			return new AlertaRupturaDTO();
		case PRECO:
			return new AlertaPrecoDTO();
		case PARTICIPACAO:
			return new AlertaParticipacaoDTO();
		default:
			throw new TipoAlertaInvalidoException();
		}
	}
}
