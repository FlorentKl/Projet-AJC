package projetSpringBoot.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    // La configuration des langue dans spring boot a besoin aussi d'une classe de
    // configuration, Spring boot ne le gère pas de base
    // Cependant, pas besoin d'un MessageSource car les fichiers properties ont le
    // nom messages.
    // Cependant si fichiers ont nom différent, il y a alors besoin d'une methode
    // MessageSource.
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setCookieName("lang");
        clr.setDefaultLocale(Locale.FRENCH);
        return clr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
