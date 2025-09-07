package org.angulo.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LibraryUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final LibraryUserDetailsService libraryUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = libraryUserDetailsService.loadUserByUsername(authentication.getName());
        String passwordAuthentication = authentication.getCredentials().toString();

        if(passwordEncoder.matches(passwordAuthentication, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                    authentication.getName(),
                    passwordAuthentication,
                    userDetails.getAuthorities()
            );
        }else {
            throw new BadCredentialsException("Invalid password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

    }
}
