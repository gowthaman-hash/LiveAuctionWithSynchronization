package com.cars24.auction.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}password").roles("ADMIN")
                .and()
                .withUser("user2").password("{noop}password").roles("ADMIN")
                .and()
                .withUser("user3").password("{noop}password").roles("ADMIN")
                .and()
                .withUser("user4").password("{noop}password").roles("USER","ADMIN");

    }

     @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auction/**").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
