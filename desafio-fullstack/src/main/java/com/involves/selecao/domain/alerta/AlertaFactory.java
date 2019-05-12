package com.involves.selecao.domain.alerta;

import com.involves.selecao.domain.alerta.exceptions.TipoAlertaInvalidoException;
import com.involves.selecao.domain.alerta.tipo.TipoAlertaEnum;
import com.involves.selecao.domain.pesquisa.Pesquisa;

public class AlertaFactory {
	
	public static Alerta getAlerta(TipoAlertaEnum tipoAlertaEnum) {
		switch (tipoAlertaEnum) {
		case RUPTURA:
			return new AlertaRuptura();
		case PRECO:
			return new AlertaPreco();
		case PARTICIPACAO:
			return new AlertaParticipacao();
		default:
			throw new TipoAlertaInvalidoException();
		}
	}
}
