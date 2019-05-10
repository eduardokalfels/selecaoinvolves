package com.involves.selecao.domain.alerta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
				if (resposta.getPergunta().equals("Qual a situação do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
						Alerta alerta = new Alerta();
						alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
						alerta.setDescricao("Ruptura detectada!");
						alerta.setProduto(pesquisa.getProduto());
						alerta.setFlTipo(1);
						gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
					if(precoColetado > precoEstipulado){
						Alerta alerta = new Alerta();
						int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
						alerta.setMargem(margem);
						alerta.setDescricao("Preço acima do estipulado!");
						alerta.setProduto(pesquisa.getProduto());
						alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
						alerta.setFlTipo(2);
						gateway.salvar(alerta);
					} else if(precoColetado < precoEstipulado){
						Alerta alerta = new Alerta();
						int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
						alerta.setMargem(margem);
						alerta.setDescricao("Preço abaixo do estipulado!");
						alerta.setProduto(pesquisa.getProduto());
						alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
						alerta.setFlTipo(3);
						gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("%Share")) {	
					int participacaoColetada = Integer.parseInt(resposta.getResposta());
					int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacaoEstipulada());
					if(participacaoColetada > participacaoEstipulada){
						Alerta alerta = new Alerta();
						int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
						alerta.setMargem(margem);
						alerta.setDescricao("Participação acima do estipulado!");
						alerta.setCategoria(pesquisa.getCategoria());
						alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
						alerta.setFlTipo(4);
						gateway.salvar(alerta);
					} else if(participacaoColetada < participacaoEstipulada){
						Alerta alerta = new Alerta();
						int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
						alerta.setMargem(margem);
						alerta.setDescricao("Participação inferior ao estipulado!");
						alerta.setCategoria(pesquisa.getCategoria());
						alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
						alerta.setFlTipo(5);
						gateway.salvar(alerta);
					}
				} else {
					System.out.println("Alerta ainda não implementado!");
				}
			});
		});
		
	}

}
