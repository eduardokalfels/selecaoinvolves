package com.involves.selecao.domain.alerta;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.domain.alerta.dto.AlertaDTO;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

//	@Autowired
//	private AlertaService buscaAlertasService;
	
	@Autowired
	private IAlertaService alertaService;
	
	@GetMapping
    public List<AlertaDTO> alertas() {
		return alertaService.buscarTodos();
    }
	
	@GetMapping("/processar")
    public void processar() {
		try {
			alertaService.processarAlertas();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
