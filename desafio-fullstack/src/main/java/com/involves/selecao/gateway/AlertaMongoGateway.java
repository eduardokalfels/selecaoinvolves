package com.involves.selecao.gateway;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.domain.alerta.Alerta;
import com.involves.selecao.domain.alerta.AlertaFactory;
import com.involves.selecao.domain.alerta.AlertaParticipacao;
import com.involves.selecao.domain.alerta.AlertaPreco;
import com.involves.selecao.domain.alerta.AlertaRuptura;
import com.involves.selecao.domain.alerta.tipo.TipoAlertaEnum;
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
		TipoAlertaEnum tipo = alerta.getTipo();
		Document doc = new Document("ponto_de_venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", tipo.toString());
		switch (tipo) {
		case RUPTURA:
          doc = doc.append("produto", ((AlertaRuptura) alerta).getProduto());
			break;
		case PRECO:
			doc = doc.append("margem", ((AlertaPreco) alerta).getMargem())
				.append("produto", ((AlertaPreco) alerta).getProduto());
			break;
		case PARTICIPACAO:
			doc.append("margem", ((AlertaParticipacao) alerta).getMargem())
				.append("categoria", ((AlertaParticipacao) alerta).getCategoria());
			break;
		}
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos() {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		FindIterable<Document> db = collection.find();
		List<Alerta> alertas = new ArrayList<>();
		for (Document document : db) {
			TipoAlertaEnum tipo = TipoAlertaEnum.valueOf(document.getString("tipo"));
			Alerta alerta = AlertaFactory.getAlerta(tipo);
			
			alerta.setPontoDeVenda(document.getString("ponto_de_venda"));
			alerta.setDescricao(document.getString("descricao"));
			alertas.add(alerta);
			switch (tipo) {
			case RUPTURA:
				((AlertaRuptura) alerta).setProduto(document.getString("produto"));
				break;
			case PRECO:
				((AlertaPreco) alerta).setProduto(document.getString("produto"));
				((AlertaPreco) alerta).setMargem(document.getInteger("margem"));
				break;
			case PARTICIPACAO:
				((AlertaParticipacao) alerta).setCategoria(document.getString("categoria"));
				((AlertaParticipacao) alerta).setMargem(document.getInteger("margem"));
				break;
			}
		}
		return alertas;
	}
}
