package pe.edu.urp.database.core.util;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	@Bean(name="dataSource")
	public DataSource dataSource() {
	DriverManagerDataSource dataSource = new
	DriverManagerDataSource();
	DatosConexion datosConexion = new DatosConexion();
	dataSource.setDriverClassName(datosConexion.getDirver());
	dataSource.setUrl(datosConexion.getUrl());
	dataSource.setUsername(datosConexion.getUsername());
	dataSource.setPassword(datosConexion.getPassword());
	return dataSource;
	}

	
}
