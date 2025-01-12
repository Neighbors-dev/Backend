package com.neighbors.tohero.common.security;

import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
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

    public static UserAuthentication makeGuestAuthentication(String nickname) {
        return new UserAuthentication(JwtUserDetails.makeGuestJwtDetails(nickname), null, Role.GUEST.getAuthority());
    }

}
