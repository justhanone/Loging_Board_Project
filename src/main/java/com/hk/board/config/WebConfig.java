package com.hk.board.config;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//spring 환경설정관련 클래스 선언
// -(root-context.xml,servlet-context.xml 등 대체 )
@Configuration 
@EnableTransactionManagement //선언해야 @Transactional을 사용 가능
public class WebConfig {
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	
	@Bean //메서드단위로 선언 및 등록
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}