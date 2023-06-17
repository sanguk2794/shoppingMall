package net.toyproject.mall.back.config;

import net.toyproject.mall.back.config.handler.LoginSuccessHandler;
import net.toyproject.mall.back.config.handler.LogoutSuccessHandler;
import net.toyproject.mall.back.config.security.BackofficeAuthenticationProvider;
import net.toyproject.mall.back.util.ConstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class BackofficeSecurityConfig {

    @Autowired
    private BackofficeAuthenticationProvider authenticationProvider;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        final AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);

        return authenticationManagerBuilder.build();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeRequests()
                // .requireCsrfProtectionMatcher(new CsrfRequestMatcher())
                // .csrfTokenRepository(csrfTokenRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/dashboard/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage(ConstUtils.LOGIN_URL)
                .loginProcessingUrl(ConstUtils.LOGIN_PROCESSING_URL)
                .failureHandler(authenticationFailureHandler)
                .defaultSuccessUrl("/dashboard", false)
                .successHandler(loginSuccessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl(ConstUtils.LOGOUT_URL)
                .logoutSuccessUrl(ConstUtils.LOGIN_URL)
                .addLogoutHandler(logoutSuccessHandler)
                .invalidateHttpSession(true)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-CSRF-TOKEN");
        return repository;
    }
}
