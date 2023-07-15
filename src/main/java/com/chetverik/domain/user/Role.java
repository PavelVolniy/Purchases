package com.chetverik.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MANAGER, ADMIN, SUPERUSER;

    @Override
    public String getAuthority() {
        return name();
    }
}
