package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.domain.alerta.Alerta;

public interface AlertaGateway {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodos();
}
