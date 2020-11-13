/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Autowired
    //  private UserService userService;
    @Value("${meteo-provider.auth.enabled}")
    private Boolean authEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (authEnabled) {
            http.authorizeRequests()
                    .antMatchers("/main").access("permitAll")
                    .antMatchers("/sms").authenticated()
                    .antMatchers("/gps").authenticated()
                    .antMatchers("/email").authenticated()
                    .antMatchers("/input*").authenticated()
                    .antMatchers("/command*").hasAnyAuthority("ROLE_OPERATOR", "ROLE_ADMIN")
                    .antMatchers("/reset*").hasAnyAuthority("ROLE_OPERATOR", "ROLE_ADMIN")
                    .antMatchers("/event").authenticated()
                    .antMatchers("/container*").hasAnyAuthority("ROLE_REGISTRAR", "ROLE_ADMIN")
                    .antMatchers("/router*").hasAnyAuthority("ROLE_REGISTRAR", "ROLE_ADMIN")
                    .antMatchers("/user*").hasAnyAuthority("ROLE_REGISTRAR", "ROLE_ADMIN")
                    .antMatchers("/login", "/registration", "index").access("permitAll")
                    .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
                    .and().logout().logoutSuccessUrl("/")
                    .and().headers().frameOptions().sameOrigin()
                    .and().exceptionHandling().accessDeniedPage("/page403.html");
        }
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }
    */
}
