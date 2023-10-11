package com.myphotos.demo.service;

import java.util.*;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MockUserDetailsService implements UserDetailsService {



    //prende come parametro username del utente per capire chi sta provando ad accedere al sistema
    //ritorna un interfaccia di tipo UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin") == false) {
                throw new UsernameNotFoundException(username + " not found");
        }
        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));  //deve precedere al ruolo la scrita "ROLE_"
                return authorities;
            }

            @Override
            public String getPassword() {
                return "{noop}admin";   //indichiamo che non usiamo nessun PasswordEncoder//spring puo confrontare la pasword inviato dall'utente che non è criptata con questa "admin"
                
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
            
        };
    }
    
}
