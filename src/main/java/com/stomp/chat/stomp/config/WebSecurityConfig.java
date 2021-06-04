package com.stomp.chat.stomp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final  String RESOURCE_ROOT = "/resources/**";

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable().authorizeRequests()
            .antMatchers("/user/login", RESOURCE_ROOT).permitAll()
            .antMatchers("/sub/**", "/pub/**").authenticated()
            .anyRequest().authenticated();
            
        http.formLogin()
            .loginPage("/user/login")
            .loginProcessingUrl("/api/login")
            .defaultSuccessUrl("/");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/denied");

    }
    
    @Bean
    public BCryptPasswordEncoder BcyptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
