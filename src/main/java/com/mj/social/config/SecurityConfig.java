package com.mj.social.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .loginPage("/login")
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorize-client");

        http.csrf().disable(); //needed for h2 console access
        http.headers().frameOptions().disable(); //needed for h2 console access
    }

}
