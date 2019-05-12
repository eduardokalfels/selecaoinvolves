package com.involves.selecao.gateway.mongo;

import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoDbFactory {
	
	private MongoClient client;
	
	/**
	 * Nome do database da aplicação, definido no application.properties 
	 * ou em variável de ambiente
	 */
	@Value("${alertas.agile.database.name}")
	private String dataBaseName;
	
	public MongoDbFactory() {
		client = MongoClients.create();
	}
	
	public MongoDatabase getDb(){
		MongoDatabase database = client.getDatabase(dataBaseName);
		return database;
	}
}
