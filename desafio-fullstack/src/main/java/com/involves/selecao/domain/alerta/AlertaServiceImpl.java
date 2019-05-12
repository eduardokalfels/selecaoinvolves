package com.involves.selecao.domain.alerta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.alerta.dto.AlertaFactory;
import com.involves.selecao.domain.alerta.dto.AlertaParticipacaoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaPrecoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaRupturaDTO;
import com.involves.selecao.domain.alerta.dto.mapper.IAlertaMapper;
import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;
import com.involves.selecao.domain.pesquisa.IPesquisaService;
import com.involves.selecao.domain.pesquisa.vo.PesquisaVO;
import com.involves.selecao.domain.pesquisa.vo.RespostaVO;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class AlertaServiceImpl implements IAlertaService {
	
	@Autowired
	private AlertaGateway gateway;
	
	@Autowired
	private IPesquisaService pesquisaService;
	
	@Autowired
	private IAlertaMapper mapper;
	
	public List<AlertaDTO> buscarTodos() {
		return this.mapper.toDTOs(gateway.buscarTodos());
	}

	@Override
	public void processarAlertas() throws IOException {
		List<PesquisaVO> pesquisas = pesquisaService.buscarTodas();
		this.processarAlertas(pesquisas);
	}
	
	@Override
	public void processarAlertas(List<PesquisaVO> pesquisas) throws IOException {
		pesquisas.forEach(pesquisa -> {
			pesquisa.getRespostas().forEach(resposta -> {
				AlertaDTO alerta = null;
				if (resposta.getPergunta().equals("Qual a situação do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
						alerta = AlertaFactory.getAlerta(TipoAlertaEnum.RUPTURA);
						alerta.setDescricao("Ruptura detectada!");
						((AlertaRupturaDTO) alerta).setProduto(pesquisa.getProduto());
					}
				} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
					int margem = precoEstipulado - precoColetado;
					if(margem != 0) {
						alerta = AlertaFactory.getAlerta(TipoAlertaEnum.PRECO);
						((AlertaPrecoDTO) alerta).setProduto(pesquisa.getProduto());
						((AlertaPrecoDTO) alerta).setMargem(margem);
						alerta.setDescricao(precoColetado > precoEstipulado ? "Preço acima do estipulado!" : "Preço abaixo do estipulado!");
					}
				} else if(resposta.getPergunta().equals("%Share")) {	
					int participacaoColetada = Integer.parseInt(resposta.getResposta());
					int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacaoEstipulada());
					int margem = participacaoEstipulada - participacaoColetada;
					if(margem != 0) {
						alerta = AlertaFactory.getAlerta(TipoAlertaEnum.PARTICIPACAO);
						((AlertaParticipacaoDTO) alerta).setCategoria(pesquisa.getCategoria());
						((AlertaParticipacaoDTO) alerta).setMargem(margem);
						alerta.setDescricao(participacaoColetada > participacaoEstipulada ? "Participação acima do estipulado!" : "Participação inferior ao estipulado!");
					}
				} else {
					System.out.println("Alerta ainda não implementado!");
				}
				
				if(alerta != null) {
					alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
					gateway.salvar(this.mapper.toEntidade(alerta));
				}
				
			});
		});
		
	}

}
