package info.maila.baseapp.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Component
@OptIn(ExperimentalTime::class)
class RequestInterceptor : HandlerInterceptor {

    private val requestStart = ThreadLocal<Instant>()

    val xRuntime = "X-Runtime"
    val activation = listOf("yes", "on", "true", "1")

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {

        if (request.isActive(xRuntime)) {
            requestStart.set(Clock.System.now())
        }

        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {

        if (requestStart.get() != null) {
            val runtime = Clock.System.now().minus(requestStart.get())
            response.setHeader(xRuntime, runtime.toString())
        }

    }

    private fun HttpServletRequest.isActive(header: String) =
        activation.contains(getHeader(header)?.lowercase())

}