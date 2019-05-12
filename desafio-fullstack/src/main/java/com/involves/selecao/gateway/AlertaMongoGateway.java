package com.involves.selecao.gateway;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.domain.alerta.Alerta;
import com.involves.selecao.domain.alerta.dto.AlertaDTO;
import com.involves.selecao.domain.alerta.dto.AlertaFactory;
import com.involves.selecao.domain.alerta.dto.AlertaParticipacaoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaPrecoDTO;
import com.involves.selecao.domain.alerta.dto.AlertaRupturaDTO;
import com.involves.selecao.domain.alerta.dto.tipo.TipoAlertaEnum;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class AlertaMongoGateway implements AlertaGateway{
	
	private static final String CATEGORIA = "categoria";
	private static final String PRODUTO = "produto";
	private static final String MARGEM = "margem";
	private static final String TIPO = "tipo";
	private static final String DESCRICAO = "descricao";
	private static final String PONTO_DE_VENDA = "ponto_de_venda";
	
	private static final String ALERTAS_COLLECTION = "Alertas";
	
	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection(ALERTAS_COLLECTION);
		Document doc = new Document(PONTO_DE_VENDA, alerta.getPontoDeVenda())
                .append(DESCRICAO, alerta.getDescricao())
                .append(TIPO, alerta.getTipo())
                .append(MARGEM, alerta.getMargem())
                .append(PRODUTO, alerta.getProduto())
                .append(CATEGORIA, alerta.getCategoria());
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos() {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection(ALERTAS_COLLECTION);
		FindIterable<Document> db = collection.find();
		List<Alerta> alertas = new ArrayList<>();
		for (Document document : db) {
			Alerta alerta = new Alerta();
			alerta.setDescricao(document.getString(DESCRICAO));
			alerta.setTipo(document.getString(TIPO));
			alerta.setMargem(document.getInteger(MARGEM));
			alerta.setPontoDeVenda(document.getString(PONTO_DE_VENDA));
			alerta.setProduto(document.getString(PRODUTO));
			alerta.setCategoria(document.getString(CATEGORIA));
			alertas.add(alerta);
		}
		return alertas;
	}
}
