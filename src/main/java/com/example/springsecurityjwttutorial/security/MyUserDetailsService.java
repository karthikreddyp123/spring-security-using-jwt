package com.example.springsecurityjwttutorial.security;

import com.example.springsecurityjwttutorial.entity.Role;
import com.example.springsecurityjwttutorial.entity.User;
import com.example.springsecurityjwttutorial.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userRes = userRepo.findByEmail(email);
        if (userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        User user = userRes.get();
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return new ArrayList<>(roles);
    }
}
