package info.maila.baseapp.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.UUID.randomUUID

@Component
class RequestFilter : OncePerRequestFilter() {

    private val mdcRequestId = "request_id"
    private val mdcRequestIdFormatted = "${mdcRequestId}_formatted"
    val xRequestId = "X-Request-Id"

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val requestId = request.getHeader(xRequestId) { randomUUID().toString() }
        response.setHeader(xRequestId, requestId)

        MDC.put(mdcRequestId, requestId)
        MDC.put(mdcRequestIdFormatted, " [rq:$requestId]")

        try {

            filterChain.doFilter(request, response)

        } finally {

            MDC.remove(mdcRequestIdFormatted)
            MDC.remove(mdcRequestId)

        }

    }

    private fun HttpServletRequest.getHeader(header: String, default: () -> String) =
        getHeader(header) ?: default()

}