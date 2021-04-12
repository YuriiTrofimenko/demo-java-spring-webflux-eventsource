package org.tyaa.demo.java.springboot.webflux.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "org.tyaa.demo.java.springboot.webflux.repository")
public class RepositoryConfig extends AbstractReactiveMongoConfiguration {

	@Value("${port}")
	private String port;
	@Value("${dbname}")
	private String dbName;

        // Depricated
	/*@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return MongoClients.create();
	}*/
        
        // Modern
        @Override
        public MongoClient reactiveMongoClient() {
            return MongoClients.create();
        }

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return dbName;
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}
}
