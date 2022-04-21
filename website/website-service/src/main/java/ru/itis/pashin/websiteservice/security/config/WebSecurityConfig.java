package ru.itis.pashin.websiteservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERNAME_PARAMETER = "email";
    private static final String ADMIN_ROLE_CODE = "ADMIN";
    private static final String USER_ROLE_CODE = "USER";
    private static final String BANK_EMPLOYEE_ROLE_CODE = "BANK_EMPLOYEE";
    private static final String SESSION_COOKIE_NAME = "SESSION";
    private static final String REMEMBER_ME_COOKIE_NAME = "remember-me";


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final PersistentTokenRepository persistentTokenRepository;

    @Autowired
    public WebSecurityConfig(@Qualifier(value = "SiteUserDerailsService") UserDetailsService userDetailsService,
                             PasswordEncoder passwordEncoder, PersistentTokenRepository persistentTokenRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/website/auth/**", "/website/confirm/**", "/").permitAll()
                .antMatchers("/website/main").authenticated()
                .antMatchers("/website/admin/**").hasAuthority(ADMIN_ROLE_CODE)
                .antMatchers("/website/loan/**").hasAnyAuthority(USER_ROLE_CODE, BANK_EMPLOYEE_ROLE_CODE)
                .and()
                .rememberMe()
                .rememberMeParameter(REMEMBER_ME_COOKIE_NAME)
                .tokenRepository(persistentTokenRepository);
        http.formLogin()
                .loginPage("/website/auth/signIn")
                .usernameParameter(USERNAME_PARAMETER)
                .defaultSuccessUrl("/website/main")
                .failureUrl("/website/auth/signIn?error")
                .permitAll();
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/website/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies(SESSION_COOKIE_NAME, REMEMBER_ME_COOKIE_NAME)
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
