package info.maila.baseapp.domain.music

import info.maila.baseapp.common.rest.TablePage
import info.maila.baseapp.common.rest.TablePageable
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder

@RestController
@RequestMapping(path = ["/api/tracks"])
class TrackRestController(private val service: TrackService) {

    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun overview(
        pageable: TablePageable,
        rq: HttpServletRequest
    ): TablePage<TrackOverview> {
        logger.trace {
            val queryStr = rq.queryString
                ?.let { URLDecoder.decode(it, Charsets.UTF_8) }
                ?: ""
            "${rq.requestURI}?$queryStr"
        }
        logger.trace { pageable }
        return service.findAll(pageable)
    }

    @GetMapping(path = ["/{trackId}"])
    fun track(@PathVariable trackId: Long) = service.findTrackById(trackId)

    @GetMapping(path = ["/{trackId}/artworks"])
    fun trackArtworks(@PathVariable trackId: Long) = service.findTrackArtworkMetadata(trackId)

    @GetMapping(path = ["/{trackId}/artworks/{index}"])
    fun trackArtwork(@PathVariable trackId: Long, @PathVariable index: Int): List<TrackArtwork> {
        require(index > 0) { "index must be greater than 0" }
        return service.findTrackArtworks(trackId, index - 1)
    }

}