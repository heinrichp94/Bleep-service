//package com.censored.multitenantadapter.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "DataSourceConfig")
//@Data
//public class DataSourceConfig {
//    private static final long serialVersionUID = 5104181924076372196L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String name;
//    private String url;
//    private String username;
//    private String password;
//    private String driverClassName;
//    private boolean initialize;
//
//    public DataSourceConfig(String name, String url, String username, String password, String driverClassName, boolean initialize) {
//        this.name = name;
//        this.url = url;
//        this.username = username;
//        this.password = password;
//        this.driverClassName = driverClassName;
//        this.initialize = initialize;
//    }
//}
