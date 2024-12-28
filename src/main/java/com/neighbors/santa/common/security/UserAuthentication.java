package com.neighbors.santa.common.security;

import com.neighbors.santa.common.enums.Role;
import com.neighbors.santa.common.jwt.JwtUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static UserAuthentication from(JwtUserDetails jwtUserDetails) {
        return new UserAuthentication(jwtUserDetails, null, jwtUserDetails.getRole().getAuthority());
    }

    public static UserAuthentication makeGuestAuthentication() {
        return new UserAuthentication(JwtUserDetails.makeGuestJwtDetails(), null, Role.GUEST.getAuthority());
    }

}
