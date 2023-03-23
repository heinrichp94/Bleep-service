package com.censored.multitenantadapter.configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {

    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    public static void setCurrentTenant(String tenant) {
        log.info("current Tenant {}",getCurrentTenant());
        CURRENT_TENANT.set(tenant);
        log.info("Changed to Tenant {}",getCurrentTenant());

    }
}
