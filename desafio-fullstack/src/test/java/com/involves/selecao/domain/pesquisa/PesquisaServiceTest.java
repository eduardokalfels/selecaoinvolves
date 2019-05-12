package com.involves.selecao.domain.pesquisa;

import com.involves.selecao.domain.pesquisa.IPesquisaService;
import com.involves.selecao.domain.pesquisa.vo.PesquisaVO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PesquisaServiceTest {

	@AutoWired
	private IPesquisaService pesquisaService;
	
	@Test
	public void buscarTodasTest() {
		List<PesquisaVO> pesquisas = pesquisaService.buscarTodas();
		assertThat(pesquisas != null);
		assertThat(!pesquisas.isEmpty());
	}
}
