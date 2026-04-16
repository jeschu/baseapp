package info.maila.baseapp.config.error

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.webmvc.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class CustomErrorController : ErrorController {

    private val logger = KotlinLogging.logger { }

    @RequestMapping("/error")
    fun handleError(model: Model, request: HttpServletRequest): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
            ?.toString()
            ?.let(Integer::valueOf)
        val message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE)?.toString()
        val requestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)?.toString()
        val exception: Any? = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)

        logger.error { "handleError - status: $status, message: $message, requestUri: $requestUri, exception: $exception" }

        model.addAttribute("status", status)
        model.addAttribute("message", message)
        model.addAttribute("path", requestUri)
        model.addAttribute("exception", exception)

        return "error/error"
    }

}