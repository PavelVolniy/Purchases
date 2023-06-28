package com.chetverik.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ACCOUNTING;

    @Override
    public String getAuthority() {
        return name();
    }
}
