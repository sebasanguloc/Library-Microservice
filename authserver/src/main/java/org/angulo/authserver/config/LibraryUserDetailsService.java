package org.angulo.authserver.config;

import lombok.RequiredArgsConstructor;
import org.angulo.authserver.model.User;
import org.angulo.authserver.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LibraryUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found by email: " + username));
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getAuthority().getName())
        );
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                authorities
        );
    }

}
