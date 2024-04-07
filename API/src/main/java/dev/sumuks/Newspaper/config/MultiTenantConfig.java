package dev.sumuks.Newspaper.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.sumuks.Newspaper.entity.Tenants;
import dev.sumuks.Newspaper.repository.TenantsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@Slf4j
public class MultiTenantConfig {

    @Autowired
    Environment environment;

    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource(){

        Map<Object, Object> resolvedDataSources = new HashMap<>();

        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(environment.getProperty("spring.datasource.url"));
        dataSourceProperties.setUsername(environment.getProperty("spring.datasource.username"));
        dataSourceProperties.setPassword(environment.getProperty("spring.datasource.password"));
        dataSourceProperties.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceProperties.initializeDataSourceBuilder().build());
        List<Tenants> tenantsList = jdbcTemplate.query("SELECT * FROM tenants", new BeanPropertyRowMapper<>(Tenants.class));


        for(Tenants tenant : tenantsList){
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(tenant.getDbUrl());
            hikariConfig.setUsername(tenant.getDbUser());
            hikariConfig.setPassword(tenant.getDbPass());
            HikariDataSource dataSource = new HikariDataSource(hikariConfig);
            resolvedDataSources.put(tenant.getTenantId() ,dataSource);
            log.info("Connection established to schema : {}", tenant.getTenantId());
        }

        AbstractRoutingDataSource dataSource = new MultiTenantRoutingDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get("in"));
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();
        return dataSource;
    }
}
