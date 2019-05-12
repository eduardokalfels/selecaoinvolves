package com.involves.selecao.domain.alerta;

import java.io.IOException;
import java.util.List;

import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.pesquisa.vo.PesquisaVO;

public interface IAlertaService {
	
	/**
	 * Buscar todos os registros de alertas
	 * na base de dados
	 * 
	 * @return todos {@link AlertaDTO}s cadastrados na aplicação
	 */
	List<AlertaDTO> buscarTodos();
	
	/**
	 * Processa os alertas gerados em todas
	 * as pesquisas
	 * 
	 * @return todos {@link AlertaDTO}s cadastrados na aplicação
	 * @throws IOException 
	 */
	void processarAlertas() throws IOException;
	
	/**
	 * Processa os alertas gerados na lista
	 * de pesquisas informada
	 * 
	 * @param pesquisas
	 * @return todos {@link AlertaDTO}s cadastrados na aplicação
	 * @throws IOException 
	 */
	void processarAlertas(List<PesquisaVO> pesquisas) throws IOException;
}
