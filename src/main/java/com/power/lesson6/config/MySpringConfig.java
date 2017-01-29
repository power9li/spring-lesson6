package com.power.lesson6.config;

import com.power.lesson6.aop.aspect.MyJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Created by shenli on 2017/1/26.
 */
@Configuration
@ComponentScan("com.power.lesson6")
@PropertySource("classpath:prod.properties")
@EnableAspectJAutoProxy
@Configurable
public class MySpringConfig {

    public MySpringConfig(){
        System.out.println("MySpringConfig.MySpringConfig");
    }

    @Value(value = "${conn.url}")
    private String connectionUrl;

    @Value(value = "${conn.username}")
    private String username;

    @Value(value = "${conn.password}")
    private String password;

    @Value(value = "${conn.driver}")
    private String driverName;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(connectionUrl,username,password);
        dataSource.setDriverClassName(driverName);
        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new MyJdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(DataSource dataSource)
    {
        PlatformTransactionManager transMan = new DataSourceTransactionManager(dataSource);
        return transMan;
    }

    @Bean
    @Autowired
    public TransactionTemplate transactionTemplate(PlatformTransactionManager manager){
        TransactionTemplate template = new TransactionTemplate(manager);
        template.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        template.setReadOnly(false);
        return template;
    }
}
