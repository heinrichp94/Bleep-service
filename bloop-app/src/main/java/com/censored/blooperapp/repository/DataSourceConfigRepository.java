package com.censored.blooperapp.repository;

import com.censored.blooperapp.model.DataSourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {
//    DataSourceConfig findByName(String name);
}