package info.maila.baseapp.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor

@Configuration
class WebConfig(
    private val requestInterceptor: RequestInterceptor,
    private val localeChangeInterceptor: LocaleChangeInterceptor
) : WebMvcConfigurer {

    private val logger = KotlinLogging.logger { }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry += requestInterceptor
        registry += localeChangeInterceptor
    }

    private operator fun InterceptorRegistry.plusAssign(interceptor: HandlerInterceptor) {
        addInterceptor(interceptor)
        logger.info { "added ${interceptor::class.qualifiedName}" }
    }

}