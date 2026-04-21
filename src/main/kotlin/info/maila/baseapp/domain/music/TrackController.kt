package info.maila.baseapp.domain.music

import info.maila.baseapp.common.rest.expires
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import kotlin.time.DurationUnit.HOURS
import kotlin.time.toDuration

@Controller
@RequestMapping(path = ["/tracks"])
class TrackController(private val service: TrackService) {

    private val logger = KotlinLogging.logger { }

    @GetMapping
    fun overview(): String = "music/track-overview"

    @GetMapping(path = ["/{trackId}"])
    fun info(@PathVariable trackId: Long, model: Model, rc: HttpServletRequest): String {
        model.addAttribute(service.findTrackById(trackId))
        model.addAttribute(service.findTrackArtworkMetadata(trackId))
        model.addAttribute("referrer", rc.getHeader(HttpHeaders.REFERER))
        return "music/track-info"
    }

    @GetMapping(path = ["/{trackId}/artworks/{artworkOrder}/img"])
    fun artworksImage(
        @PathVariable trackId: Long,
        @PathVariable artworkOrder: Int
    ): ResponseEntity<ByteArray> {
        val artwork: TrackArtwork = service.findTrackArtworks(trackId, artworkOrder)
        logger.info { artwork }
        val response: ResponseEntity.BodyBuilder = ResponseEntity.ok()
        response.header("Expires", expires(1.toDuration(HOURS)))
        artwork.mimeType?.let { response.header(CONTENT_TYPE, it) }
        artwork.height?.let { if (it > 0) response.header(HEADER_IMAGE_HEIGHT, it.toString()) }
        artwork.width?.let { if (it > 0) response.header(HEADER_IMAGE_WIDTH, it.toString()) }
        artwork.imageUrl?.let { if (it.isNotEmpty()) response.header(HEADER_IMAGE_URL, it) }
        return response.body(artwork.binaryData ?: ByteArray(0))
    }

    private val HEADER_IMAGE_HEIGHT = "X-Image-Height"
    private val HEADER_IMAGE_WIDTH = "X-Image-Width"
    private val HEADER_IMAGE_URL = "X-Image-Url"

}