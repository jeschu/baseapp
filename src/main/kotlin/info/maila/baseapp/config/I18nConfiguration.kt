package info.maila.baseapp.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.time.Duration
import java.util.Locale

@Configuration
class I18nConfiguration {

    @Bean
    fun messageSource(): MessageSource = ReloadableResourceBundleMessageSource().apply {
        setBasename("classpath:messages")
        setDefaultEncoding("UTF-8")
    }

    @Bean
    fun localeResolver(): LocaleResolver = CookieLocaleResolver().apply {
        setDefaultLocale(Locale.GERMAN)
        setCookieMaxAge(Duration.ofSeconds(3600))
    }

    @Bean
    fun localeChangeInterceptor() = LocaleChangeInterceptor().apply {
        paramName = "lang"
    }

}