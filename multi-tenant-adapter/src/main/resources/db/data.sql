CREATE TABLE IF NOT EXISTS public.TenantDataSourceConfig (
                                                       id bigint PRIMARY KEY,
                                                       driverclassname VARCHAR(255),
    url VARCHAR(255),
    name VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    initialize BOOLEAN
    );
INSERT INTO  public.TenantDataSourceConfig VALUES (newid(), 'org.h2.Driver', 'jdbc:h2:file:./data/tenant1', 'tenant1', 'sa', '', true);
INSERT INTO  public.TenantDataSourceConfig VALUES (newid(), 'org.h2.Driver', 'jdbc:h2:file:./data/tenant2', 'tenant2', 'sa', '', true);