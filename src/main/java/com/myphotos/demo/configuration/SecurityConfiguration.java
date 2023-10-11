package com.myphotos.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;



@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")    //posso essere acceduti solo se l'utente ha il ruolo ADMIN
        .antMatchers("/admin/**").hasRole("ADMIN") //posso essere acceduti solo se l'utente ha il ruolo ADMIN
        .antMatchers("/").permitAll()   //gli altri path sono ad acesso libero
        .and().formLogin()  //autenticazione tramite il form di login
        .loginPage("/login")    //la pagina che mostrerà il form "fatto da noi"
        .and().csrf().disable();    //permete alle API di funzionare
    }

    //indica a spring il service per caricare l'utente
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService); //dobiamo usare il suo userdetailservice
        //per modificarlo dobiamo creare una classe che estende userDeailsService 
    }
    
    
    
}


