package com.involves.selecao.domain.pesquisa;

import java.io.IOException;
import java.util.List;

public interface IPesquisaService {
	
	/**
	 * Buscar todas as pesquisas cadastradas
	 * 
	 * @return todas as {@link Pesquisa}s
	 * @throws IOException 
	 */
	List<Pesquisa> buscarTodas() throws IOException;
	
}
