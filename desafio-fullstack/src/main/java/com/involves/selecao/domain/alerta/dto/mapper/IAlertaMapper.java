package com.involves.selecao.domain.alerta.dto.mapper;

import java.util.List;

import com.involves.selecao.domain.alerta.Alerta;
import com.involves.selecao.domain.alerta.dto.AlertaDTO;

public interface IAlertaMapper {

	AlertaDTO toDTO(Alerta alerta);
	
	List<AlertaDTO> toDTOs(List<Alerta> alerta);
	
	Alerta toEntidade(AlertaDTO alerta);
	
	List<Alerta> toEntidades(List<AlertaDTO> alerta);
}
