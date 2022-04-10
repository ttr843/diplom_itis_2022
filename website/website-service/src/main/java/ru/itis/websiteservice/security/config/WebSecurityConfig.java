package ru.itis.websiteservice.security.config;

import lombok.RequiredArgsConstructor;
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

import static ru.itis.websiteservice.security.enumeration.Endpoints.*;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERNAME_PARAMETER = "email";
    private static final String SESSION_COOKIE_NAME = "SESSION";
    private static final String REMEMBER_ME_COOKIE_NAME = "remember-me";
    private static final String USER_DETAILS_QUALIFIER = "SiteUserDerailsService";
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private PersistentTokenRepository persistentTokenRepository;


    @Autowired
    public WebSecurityConfig(@Qualifier(value = USER_DETAILS_QUALIFIER) UserDetailsService userDetailsService,
                             PasswordEncoder passwordEncoder, PersistentTokenRepository persistentTokenRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(SIGN_UP.getUrl(), CONFIRM.getUrl(), SIGN_IN.getUrl(), ROOT.getUrl()).permitAll()
                .antMatchers(MAIN.getUrl()).authenticated()
                .and()
                .rememberMe()
                .rememberMeParameter(REMEMBER_ME_COOKIE_NAME)
                .tokenRepository(persistentTokenRepository);


        http.formLogin()
                .loginPage(SIGN_IN.getUrl())
                .usernameParameter(USERNAME_PARAMETER)
                .defaultSuccessUrl(ROOT.getUrl())
                .failureUrl(SIGN_IN_ERROR.getUrl())
                .permitAll();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT.getUrl()))
                .logoutSuccessUrl(ROOT.getUrl())
                .deleteCookies(SESSION_COOKIE_NAME, REMEMBER_ME_COOKIE_NAME)
                .invalidateHttpSession(true);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
