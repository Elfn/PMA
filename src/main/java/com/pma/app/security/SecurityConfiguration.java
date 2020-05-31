package com.pma.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by Elimane on May, 2020, at 16:02
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

//-------------------------------------AUTHENTICATION(WHO ARE YOU?)------------------------------------------------
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        //BASIC IN MEMORY AUTHENTICATION
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}admin")
//                .roles("ADMIN")
//             .and()
//                .withUser("root")
//                .password("{noop}root")
//                .roles("USER")
//             .and()
//                .withUser("roy")
//                .password("{noop}pass")
//                .roles("USER");
//
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //JDBC PACKED SECURITY

        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled "+
                        "from user_accounts where username = ?" )
                .authoritiesByUsernameQuery("select username, role "+
                        "from user_accounts where username = ?")
                .dataSource(dataSource)
               .passwordEncoder(bCryptEncoder);

    }

    //-------------------------------------AUTORISATION(WHAT ARE YOU AUTORIZE TO DO?)------------------------------------------------

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //BASIC IN MEMORY AUTORIZATION
        //In that config only ADMIN has right to access projects form to add new project
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/","/**").permitAll()
                .and().formLogin();

        http.csrf().disable();
                //.and().formLogin().loginPage("/login-page");

//        .antMatchers("/employees/new")
//                .hasRole("ADMIN")
//                .antMatchers("/").authenticated()//Anyone authenticated(ALL)


    }


//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
