package com.involves.selecao.domain.alerta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.alerta.dto.AlertaFactory;
import com.involves.selecao.domain.alerta.dto.AlertaParticipacaoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaPrecoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaRupturaDTO;
import com.involves.selecao.domain.alerta.dto.mapper.IAlertaMapper;
import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertaMapperTest {

	@AutoWired
	private IAlertaMapper alertaMapper;
	
	@Test
	public void toDTOTest() {
		String descricao = "descricao";
		String pontoDeVenda = "ponto venda";
		String produto = "produto";
		String tipo = TipoAlertaEnum.RUPTURA.toString();

		Alerta alerta = new Alerta();
		alerta.setDescricao(descricao);
		alerta.setPontoDeVenda(pontoDeVenda);
		alerta.setTipo(tipo);
		alerta.setProduto(produto);
		AlertaDTO alertaDto = alertaMapper.toDTO(alerta);
		assertThat(alertaDto != null);
		assertThat(Objects.equals(alertaDto.getPontoDeVenda(), pontoDeVenda));
		assertThat(Objects.equals(alertaDto.getDescricao(), descricao));
		assertThat(Objects.equals(alertaDto.getTipo(), tipo));
		assertThat(alertaDto instanceof AlertaRupturaDTO);
		assertThat(Objects.equals(((AlertaRupturaDTO) alertaDto).getProduto(), produto));
		
		tipo = TipoAlertaEnum.PARTICIPACAO.toString();
		String categoria = "categoria";
		Integer margem = 5;
		
		alerta = new Alerta();
		alerta.setDescricao(descricao);
		alerta.setPontoDeVenda(pontoDeVenda);
		alerta.setTipo(tipo);
		alerta.setCategoria(categoria);
		alerta.setMargem(margem);
		
		AlertaDTO alertaDto = alertaMapper.toDTO(alerta);
		assertThat(alertaDto != null);
		assertThat(Objects.equals(alertaDto.getPontoDeVenda(), pontoDeVenda));
		assertThat(Objects.equals(alertaDto.getDescricao(), descricao));
		assertThat(Objects.equals(alertaDto.getTipo(), tipo));
		assertThat(alertaDto instanceof AlertaParticipacaoDTO);
		assertThat(Objects.equals(((AlertaParticipacaoDTO) alertaDto).getCategoria(), categoria));
		assertThat(Objects.equals(((AlertaParticipacaoDTO) alertaDto).getMargem(), margem));
		
		tipo = TipoAlertaEnum.PRECO.toString();
		
		alerta = new Alerta();
		alerta.setDescricao(descricao);
		alerta.setPontoDeVenda(pontoDeVenda);
		alerta.setTipo(tipo);
		alerta.setProduto(produto);
		alerta.setMargem(margem);
		
		AlertaDTO alertaDto = alertaMapper.toDTO(alerta);
		assertThat(alertaDto != null);
		assertThat(Objects.equals(alertaDto.getPontoDeVenda(), pontoDeVenda));
		assertThat(Objects.equals(alertaDto.getDescricao(), descricao));
		assertThat(Objects.equals(alertaDto.getTipo(), tipo));
		assertThat(alertaDto instanceof AlertaPrecoDTO);
		assertThat(Objects.equals(((AlertaPrecoDTO) alertaDto).getProduto(), produto));
		assertThat(Objects.equals(((AlertaPrecoDTO) alertaDto).getMargem(), margem));
	}
	
	@Test
	public void toEntidadeTest() {
		String descricao = "descricao";
		String pontoDeVenda = "ponto venda";
		String produto = "produto";
		String tipo = TipoAlertaEnum.RUPTURA.toString();

		AlertaDTO alertaDto = AlertaFactory.getAlerta(tipo);
		alertaDto.setDescricao(descricao);
		alertaDto.setPontoDeVenda(pontoDeVenda);
		((AlertaRupturaDTO) alertaDto).setProduto(produto);
		Alerta alerta = alertaMapper.toEntidade(alertaDto);
		assertThat(alerta != null);
		assertThat(Objects.equals(alerta.getPontoDeVenda(), pontoDeVenda));
		assertThat(Objects.equals(alerta.getDescricao(), descricao));
		assertThat(Objects.equals(alerta.getTipo(), tipo));
		assertThat(Objects.equals(alerta.getProduto(), ((AlertaRupturaDTO) alertaDto).getProduto()));
	}
	
}
