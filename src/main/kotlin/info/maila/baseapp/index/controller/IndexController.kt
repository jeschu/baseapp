package info.maila.baseapp.index.controller

import info.maila.baseapp.config.git.GitInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["/"])
class IndexController(
    private val gitInfo: GitInfo,
    private val applicationContext: ApplicationContext
) {

    private val logger = KotlinLogging.logger { }

    @GetMapping
    fun index(model: Model): String {
        val version = applicationContext.applicationName.ifBlank { "BaseApp" } +
                " (${gitInfo.version()})"
        model.addAttribute("version", version)
        logger.info { "INFO" }
        return "index"
    }

    private fun GitInfo.version(): String =
        listOfNotNull(
            buildVersion?.ifBlank { null },
            totalCommitCount?.let { "-b$it" },
            if (dirty != false) "*" else null
        ).joinToString("")

}