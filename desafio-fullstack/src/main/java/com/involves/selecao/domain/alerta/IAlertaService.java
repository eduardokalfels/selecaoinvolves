package com.involves.selecao.domain.alerta;

import java.io.IOException;
import java.util.List;

public interface IAlertaService {
	
	/**
	 * Buscar todos os registros de alertas
	 * na base de dados
	 * 
	 * @return todos {@link Alerta}s cadastrados na aplicação
	 */
	List<Alerta> buscarTodos();
	
	/**
	 * Processa todas
	 * 
	 * @return todos {@link Alerta}s cadastrados na aplicação
	 * @throws IOException 
	 */
	void processarAlertas() throws IOException;
}
