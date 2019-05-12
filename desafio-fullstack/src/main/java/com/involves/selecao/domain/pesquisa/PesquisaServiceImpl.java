package com.involves.selecao.domain.pesquisa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.pesquisa.vo.PesquisaVO;

@Service
public class PesquisaServiceImpl implements IPesquisaService {
	
	/**
	 * URL definida no application.properties, ou em variável de ambiente
	 */
	@Value("${alertas.agile.pesquisas.url}")
	private String URL_PESQUISAS;

	public List<PesquisaVO> buscarTodas() throws IOException {
		URL url = new URL(URL_PESQUISAS);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		  new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		Type listaPesquisaType = new TypeToken<List<PesquisaVO>>(){}.getType();
		return gson.fromJson(content.toString(), listaPesquisaType);
	}

}
