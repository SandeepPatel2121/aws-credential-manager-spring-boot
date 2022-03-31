package com.example.demo;

import com.example.demo.aws.credentials.AwsCredentialsManager;
import com.example.demo.models.DataModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() {
		DataSource ds = null;
		DataModel dataModel = AwsCredentialsManager.getSecret();
		if (dataModel != null) {
			DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

			dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSourceBuilder.url("jdbc:"+dataModel.getEngine()+"://"+dataModel.getHost()+";databaseName="+dataModel.getDbname());
			dataSourceBuilder.username(dataModel.getUsername());
			dataSourceBuilder.password(dataModel.getPassword());

			ds = dataSourceBuilder.build();
		}
		return ds;
	}

}
