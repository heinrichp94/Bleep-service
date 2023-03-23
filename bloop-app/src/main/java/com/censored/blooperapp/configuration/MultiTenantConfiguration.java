package com.censored.blooperapp.configuration;

import com.censored.blooperapp.model.DataSourceConfig;
import com.censored.blooperapp.repository.DataSourceConfigRepository;
import com.censored.multitenantadapter.configuration.MultiTenantDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MultiTenantConfiguration {

    @Autowired
    private  DataSourceConfigRepository dataSourceConfigRepository;

//    @PostConstruct
//    public void inti() {
//        List<DataSourceConfig> dataSourceConfigs = dataSourceConfigRepository.findAll();
//        if (!dataSourceConfigs.isEmpty()) {
//            return;
//        }
//        DataSourceConfig commonConfig = new DataSourceConfig("common", "jdbc:h2:file:./data/common", "sa", "", "org.h2.Driver", true);
//        DataSourceConfig tenant1Config = new DataSourceConfig("tenant1", "jdbc:h2:file:./data/tenant1", "sa", "", "org.h2.Driver", true);
//        DataSourceConfig tenant2Config = new DataSourceConfig("tenant2", "jdbc:h2:file:./data/tenant2", "sa", "", "org.h2.Driver", true);
//        dataSourceConfigRepository.saveAll(List.of(commonConfig, tenant1Config, tenant2Config));
//    }

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (DataSourceConfig dataSourceConfig : dataSourceConfigRepository.findAll()) {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

            String tenantId = dataSourceConfig.getName();

            dataSourceBuilder.driverClassName(dataSourceConfig.getDriverClassName());
            dataSourceBuilder.username(dataSourceConfig.getUsername());
            dataSourceBuilder.password(dataSourceConfig.getPassword());
            dataSourceBuilder.url(dataSourceConfig.getUrl());
            resolvedDataSources.put(tenantId, dataSourceBuilder.build());
        }

        AbstractRoutingDataSource dataSource = new MultiTenantDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get("common"));
        dataSource.setTargetDataSources(resolvedDataSources);

        dataSource.afterPropertiesSet();
        return dataSource;
    }

}
