package com.example.demo.securityFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }
    @Autowired
    private LoginSuccessfullHandler loginSuccessfullHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/dangky/**").hasAnyAuthority("SINH_VIEN")
                .antMatchers("/giaovu/**").hasAnyAuthority("GIANG_VIEN","GIAO_VU")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(loginSuccessfullHandler)
                .and()
                .logout();
//        http.authorizeHttpRequests()
//                .antMatchers("/**").permitAll();
    }
}
