package com.involves.selecao.domain.alerta;

import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.pesquisa.IPesquisaService;
import com.involves.selecao.domain.pesquisa.vo.PesquisaVO;
import com.involves.selecao.domain.pesquisa.vo.RespostaVO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertaServiceTest {

	@AutoWired
	private IAlertaService alertaService;
	
	@Test
	public void buscarTodosTest() {
		List<AlertaDTO> alertas = alertaService.buscarTodos();
		assertThat(alertas != null);
		assertThat(!alertas.isEmpty());
	}
	
	@Test
	public void processarTodosTest() {
		alertaService.processarAlertas();
		List<AlertaDTO> alertas = alertaService.buscarTodos();
		assertThat(alertas != null);
		assertThat(!alertas.isEmpty());
	}
	
	@Test
	public void processarTest() {
		List<PesquisaVO> pesquisas = new ArrayList<PesquisaVO>();
		alertaService.processarAlertas(pesquisas);
		List<AlertaDTO> alertas = alertaService.buscarTodos();
		assertThat(alertas == null);
		assertThat(alertas.isEmpty());
		
		pesquisas.add(producePesquisaVO());
		alertaService.processarAlertas(pesquisas);
		alertas = alertaService.buscarTodos();
		assertThat(alertas != null);
		assertThat(!alertas.isEmpty());
		assertThat(alertas.size() == 1);
	}

	private PesquisaVO producePesquisaVO() {
		PesquisaVO pesquisa = new PesquisaVO();
		pesquisa.setId(1);
		pesquisa.setRotulo("Campanha de pascoa");
		pesquisa.setNotificante("João");
		pesquisa.setPonto_de_venda("Angel One Capoeiras");
		pesquisa.setProduto("Ovo de Pascoa Kinder 48");
		
		List<RespostaVO> respostas = new ArrayList<RespostaVO>();
		RespostaVO respostaVO = new RespostaVO();
		respostaVO.setPergunta("Qual a situação do produto?");
		respostaVO.setResposta("Produto está na gondola");
		respostas.add(respostaVO);
		pesquisa.setRespostas(respostas);
//		""id":"1","rotulo":"Campanha de pascoa","notificante":"João","ponto_de_venda":"Angel One Capoeiras","produto":"Ovo de Pascoa Kinder 48","respostas":[{"pergunta":"Qual a situação do produto?","resposta":"Produto está na gondola"}";
		return pesquisa;s
	}
}
