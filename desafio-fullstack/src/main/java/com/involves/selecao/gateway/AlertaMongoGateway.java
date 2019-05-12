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
	
	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		Document doc = new Document("ponto_de_venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", alerta.getTipo())
                .append("margem", alerta.getMargem())
                .append("produto", alerta.getProduto())
                .append("categoria", alerta.getCategoria());
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos() {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		FindIterable<Document> db = collection.find();
		List<Alerta> alertas = new ArrayList<>();
		for (Document document : db) {
			Alerta alerta = new Alerta();
			alerta.setDescricao(document.getString("descricao"));
			alerta.setTipo(document.getString("tipo"));
			alerta.setMargem(document.getInteger("margem"));
			alerta.setPontoDeVenda(document.getString("ponto_de_venda"));
			alerta.setProduto(document.getString("produto"));
			alerta.setCategoria(document.getString("categoria"));
			alertas.add(alerta);
		}
		return alertas;
	}
}
