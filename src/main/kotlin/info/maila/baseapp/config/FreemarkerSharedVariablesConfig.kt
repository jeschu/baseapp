package info.maila.baseapp.config

import freemarker.ext.beans.BeansWrapperBuilder
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import freemarker.template.Configuration as FreemarkerConfiguration

@Configuration
class FreemarkerSharedVariablesConfig(val freemarkerConfiguration: FreemarkerConfiguration) {

    private val logger = KotlinLogging.logger {}

    @PostConstruct
    fun setSharedVariables() {
        freemarkerConfiguration.setSharedVariable(
            "statics",
            BeansWrapperBuilder(FreemarkerConfiguration.VERSION_2_3_34).build().staticModels
        )
        logger.info { """setSharedVariable("statics")""" }
        freemarkerConfiguration.addAutoInclude("freemarker_implicit.ftl" +
                "")
        logger.info { """addAutoInclude("freemarker_implicit.ftl")""".trimMargin() }
    }

}