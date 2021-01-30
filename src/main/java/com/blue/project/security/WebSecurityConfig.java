package com.blue.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static String[] PUBLIC_APIS = {
            "/actuator/**",
            "/public/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/v2/**",
            "/swagger-resources/**"};

    @Autowired
    private DataSource dataSource;

    @Override
    @Bean(BeanIds.USER_DETAILS_SERVICE)
    public UserDetailsService userDetailsServiceBean() {
        return new JdbcUserDetailsService();
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("userPasswordEncoder")
    PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(userPasswordEncoder());
        // BCryptPasswordEncoder(4) is used for users.password column
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> cfg = auth.jdbcAuthentication()
                .passwordEncoder(userPasswordEncoder()).dataSource(dataSource).usersByUsernameQuery("select username,password,enabled "
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, sr.authority "
                        + "from user_security_role usr "
                        + "inner join user u on u.id = usr.user_id "
                        + "inner join security_role sr on sr.id = usr.security_role_id "
                        + "where u.username = ?");

        cfg.getUserDetailsService().setEnableGroups(false);
        cfg.getUserDetailsService().setEnableAuthorities(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_APIS).permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {

        web.ignoring().antMatchers(PUBLIC_APIS);
    }

}
