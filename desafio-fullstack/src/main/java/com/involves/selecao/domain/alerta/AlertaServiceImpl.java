package com.involves.selecao.domain.alerta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.domain.alerta.tipo.TipoAlertaEnum;
import com.involves.selecao.domain.pesquisa.IPesquisaService;
import com.involves.selecao.domain.pesquisa.Pesquisa;
import com.involves.selecao.domain.pesquisa.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class AlertaServiceImpl implements IAlertaService {
	
	@Autowired
	private AlertaGateway gateway;
	
	@Autowired
	private IPesquisaService pesquisaService;
	
	public List<Alerta> buscarTodos() {
		return gateway.buscarTodos();
	}

	@Override
	public void processarAlertas() throws IOException {
		List<Pesquisa> pesquisas = pesquisaService.buscarTodas();
		pesquisas.forEach(pesquisa -> {
			pesquisa.getRespostas().forEach(resposta -> {
				Alerta alerta = null;
				if (resposta.getPergunta().equals("Qual a situação do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
						alerta = AlertaFactory.getAlerta(TipoAlertaEnum.RUPTURA);
						alerta.setDescricao("Ruptura detectada!");
						((AlertaRuptura) alerta).setProduto(pesquisa.getProduto());
					}
				} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
					int margem = precoEstipulado - precoColetado;
					if(margem != 0) {
						alerta = AlertaFactory.getAlerta(TipoAlertaEnum.PRECO);
						((AlertaPreco) alerta).setProduto(pesquisa.getProduto());
						((AlertaPreco) alerta).setMargem(margem);
						alerta.setDescricao(precoColetado > precoEstipulado ? "Preço acima do estipulado!" : "Preço abaixo do estipulado!");
					}
				} else if(resposta.getPergunta().equals("%Share")) {	
					int participacaoColetada = Integer.parseInt(resposta.getResposta());
					int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacaoEstipulada());
					int margem = participacaoEstipulada - participacaoColetada;
					if(margem != 0) {
						alerta = AlertaFactory.getAlerta(TipoAlertaEnum.PARTICIPACAO);
						((AlertaParticipacao) alerta).setCategoria(pesquisa.getCategoria());
						((AlertaParticipacao) alerta).setMargem(margem);
						alerta.setDescricao(participacaoColetada > participacaoEstipulada ? "Participação acima do estipulado!" : "Participação inferior ao estipulado!");
					}
				} else {
					System.out.println("Alerta ainda não implementado!");
				}
				
				if(alerta != null) {
					alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
					gateway.salvar(alerta);
				}
				
			});
		});
		
	}

}
