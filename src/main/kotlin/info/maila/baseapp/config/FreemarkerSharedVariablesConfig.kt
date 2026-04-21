package info.maila.baseapp.config

import freemarker.ext.beans.BeansWrapperBuilder
import freemarker.template.SimpleSequence
import freemarker.template.TemplateModel
import freemarker.template.TemplateScalarModel
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import java.util.Locale
import java.util.UUID
import freemarker.template.Configuration as FreemarkerConfiguration

@Configuration
class FreemarkerSharedVariablesConfig(
    private val freemarkerConfiguration: FreemarkerConfiguration,
    private val messageSource: MessageSource,
    private val localeResolver: LocaleResolver,
    private val httpServletRequest: HttpServletRequest
) {

    private val logger = KotlinLogging.logger {}

    @PostConstruct
    fun postConfiguration() {

        freemarkerConfiguration.setSharedVariable(
            "statics",
            BeansWrapperBuilder(FreemarkerConfiguration.VERSION_2_3_34).build().staticModels
        )
        logger.info { """setSharedVariable("statics")""" }

        freemarkerConfiguration.setSharedVariable(
            "utils",
            FreemarkerUtils(messageSource, localeResolver, httpServletRequest)
        )
        logger.info { """setSharedVariable("utils", FreemarkerUtils)""" }

        freemarkerConfiguration.addAutoInclude("freemarker_implicit.ftl")
        logger.info { """addAutoInclude("freemarker_implicit.ftl")""" }

    }

    class FreemarkerUtils(
        private val messageSource: MessageSource,
        private val localeResolver: LocaleResolver,
        private val httpServletRequest: HttpServletRequest
    ) {

        private val logger = KotlinLogging.logger {}

        @JvmOverloads
        fun message(labelKey: String, values: SimpleSequence? = null): String {
            val locale: Locale = localeResolver.resolveLocale(httpServletRequest)
            val args = mutableListOf<String>()
            if (values != null) {
                for (idx in 0 until values.size()) {
                    val templateModel: TemplateModel = values.get(idx)
                    args += if (templateModel is TemplateScalarModel) {
                        templateModel.asString
                    } else {
                        templateModel.toString()
                    }
                }
            }
            val message = try {
                messageSource.getMessage(
                    labelKey,
                    args.toTypedArray(),
                    "{{${labelKey}}}",
                    locale
                )
            } catch (e: Exception) {
                logger.error(e) { "Error while fetching message for key: $labelKey with args: $args and locale: $locale" }
                "{#${e.localizedMessage}#}"
            }
            return message ?: "{{$labelKey}}"
        }

        @JvmOverloads
        fun uniqueJavaScriptName(prefix: String? = null): String =
            (if (prefix == null) "" else "${prefix}_") +
                    UUID.randomUUID().toString().replace("-", "")

    }

}