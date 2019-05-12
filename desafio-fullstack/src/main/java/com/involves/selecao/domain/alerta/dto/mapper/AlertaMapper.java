package com.involves.selecao.domain.alerta.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.involves.selecao.domain.alerta.Alerta;
import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.alerta.dto.AlertaFactory;
import com.involves.selecao.domain.alerta.dto.AlertaParticipacaoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaPrecoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaRupturaDTO;

@Component
public class AlertaMapper implements IAlertaMapper {

	@Override
	public AlertaDTO toDTO(Alerta alerta) {
		AlertaDTO alertaDto = AlertaFactory.getAlerta(alerta.getTipo());
		alertaDto.setDescricao(alerta.getDescricao());
		alertaDto.setPontoDeVenda(alerta.getPontoDeVenda());
		
		if(alertaDto instanceof AlertaRupturaDTO) {
			((AlertaRupturaDTO) alertaDto).setProduto(alerta.getProduto());
		} else if(alertaDto instanceof AlertaPrecoDTO) {
			((AlertaPrecoDTO) alertaDto).setProduto(alerta.getProduto());
			((AlertaPrecoDTO) alertaDto).setMargem(alerta.getMargem());
		} else {
			((AlertaParticipacaoDTO) alertaDto).setCategoria(alerta.getCategoria());
			((AlertaParticipacaoDTO) alertaDto).setMargem(alerta.getMargem());
		}
		return alertaDto;
	}

	@Override
	public List<AlertaDTO> toDTOs(List<Alerta> alertas) {
		return alertas.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Alerta toEntidade(AlertaDTO alertaDto) {
		Alerta alerta = new Alerta();
		alerta.setTipo(alertaDto.getTipo().toString());
		alerta.setDescricao(alertaDto.getDescricao());
		alerta.setPontoDeVenda(alertaDto.getPontoDeVenda());
		
		if(alertaDto instanceof AlertaRupturaDTO) {
			alerta.setProduto(((AlertaRupturaDTO) alertaDto).getProduto());
		} else if(alertaDto instanceof AlertaPrecoDTO) {
			alerta.setProduto(((AlertaPrecoDTO) alertaDto).getProduto());
			alerta.setMargem(((AlertaPrecoDTO) alertaDto).getMargem());
		} else {
			alerta.setCategoria(((AlertaParticipacaoDTO) alertaDto).getCategoria());
			alerta.setMargem(((AlertaParticipacaoDTO) alertaDto).getMargem());
		}
		return alerta;
	}

	@Override
	public List<Alerta> toEntidades(List<AlertaDTO> alertasDto) {
		return alertasDto.stream()
				.map(this::toEntidade)
				.collect(Collectors.toList());
	}
	

}
