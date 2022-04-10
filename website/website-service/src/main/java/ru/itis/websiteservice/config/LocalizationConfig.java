package ru.itis.websiteservice.config;

import org.bouncycastle.util.encoders.UTF8;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Configuration
public class LocalizationConfig implements WebMvcConfigurer {

    private static final String PARAMETER_NAME = "lang";
    private static final String COOKIE_NAME = "localeInfo";
    private static final Integer COOLIE_MAX_AGE = 60 * 60 * 24 * 365;
    private static final String TEMPLATES_MESSAGE_PATH = "classpath:messages/messages";
    private static final String ENCODING_FORMAT = "UTF-8";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(PARAMETER_NAME);
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName(COOKIE_NAME);
        cookieLocaleResolver.setCookieMaxAge(COOLIE_MAX_AGE);
        return cookieLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(TEMPLATES_MESSAGE_PATH);
        messageSource.setDefaultEncoding(ENCODING_FORMAT);
        return messageSource;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
        return codesResolver;
    }
}
