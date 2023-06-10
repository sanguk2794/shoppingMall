/**
 * @author sanguk on 2023/06/10
 */

package net.toyproject.mall.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class RestApiSecurityConfig {
    private static final String[] ALLOW_URLS = {
            "/v1/member/**"
    };

    private static final String[] SWAGGER_URLS = {
            "/swagger-ui/**",
            "/swagger-resources/*",
            "/v3/api-docs/**"
    };

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers(ALLOW_URLS).permitAll()
                .antMatchers(SWAGGER_URLS).permitAll()
                .antMatchers("/v1/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .build();
    }
}
