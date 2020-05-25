package com.pma.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Elimane on May, 2020, at 20:07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
