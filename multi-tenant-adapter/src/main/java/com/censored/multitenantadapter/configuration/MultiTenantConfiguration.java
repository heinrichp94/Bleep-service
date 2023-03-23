//package com.censored.multitenantadapter.configuration;
//
//import com.censored.multitenantadapter.model.DataSourceConfig;
//import com.censored.multitenantadapter.repository.DataSourceConfigRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class MultiTenantConfiguration {
//
//    private final DataSourceConfigRepository dataSourceConfigRepository;
//
//    @PostConstruct
//    public void inti() {
//        List<DataSourceConfig> dataSourceConfigs = dataSourceConfigRepository.findAll();
//        if (!dataSourceConfigs.isEmpty()) {
//            return;
//        }
//        DataSourceConfig tenant1Config = new DataSourceConfig("org.h2.Driver", "jdbc:h2:file:./data/tenant1", "tenant1", "sa", "", true);
//        DataSourceConfig tenant2Config = new DataSourceConfig("org.h2.Driver", "jdbc:h2:file:./data/tenant2", "tenant2", "sa", "", true);
//        dataSourceConfigRepository.saveAll(List.of(tenant1Config, tenant2Config));
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        Map<Object, Object> resolvedDataSources = new HashMap<>();
//
//        for (DataSourceConfig dataSourceConfig : dataSourceConfigRepository.findAll()) {
//            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//
//            String tenantId = dataSourceConfig.getName();
//
//            dataSourceBuilder.driverClassName(dataSourceConfig.getDriverClassName());
//            dataSourceBuilder.username(dataSourceConfig.getUsername());
//            dataSourceBuilder.password(dataSourceConfig.getPassword());
//            dataSourceBuilder.url(dataSourceConfig.getUrl());
//            resolvedDataSources.put(tenantId, dataSourceBuilder.build());
//        }
//
//        AbstractRoutingDataSource dataSource = new MultiTenantDataSource();
//        dataSource.setDefaultTargetDataSource(resolvedDataSources.get("tenant1"));
//        dataSource.setTargetDataSources(resolvedDataSources);
//
//        dataSource.afterPropertiesSet();
//        return dataSource;
//    }
//
//}
