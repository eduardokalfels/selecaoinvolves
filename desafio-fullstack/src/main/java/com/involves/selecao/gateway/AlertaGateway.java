package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.domain.alerta.Alerta;
import com.involves.selecao.domain.alerta.dto.AlertaDTO;

public interface AlertaGateway {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodos();
}
