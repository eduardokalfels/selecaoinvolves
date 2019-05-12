package com.involves.selecao.domain.pesquisa;

import java.io.IOException;
import java.util.List;

import com.involves.selecao.domain.pesquisa.vo.PesquisaVO;

public interface IPesquisaService {
	
	/**
	 * Buscar todas as pesquisas cadastradas
	 * 
	 * @return todas as {@link PesquisaVO}s
	 * @throws IOException 
	 */
	List<PesquisaVO> buscarTodas() throws IOException;
	
}
