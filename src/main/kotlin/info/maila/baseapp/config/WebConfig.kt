package info.maila.baseapp.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig @Autowired constructor(
    private val requestInterceptor: RequestInterceptor
) : WebMvcConfigurer {

    private val logger = KotlinLogging.logger { }

    override fun addInterceptors(registry: InterceptorRegistry) {
        logger.debug { "adding ${RequestInterceptor::class.qualifiedName} ..." }
        registry.addInterceptor(requestInterceptor)
    }

}