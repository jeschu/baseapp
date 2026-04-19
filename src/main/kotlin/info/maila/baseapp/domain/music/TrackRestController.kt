package info.maila.baseapp.domain.music

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/tracks"])
class TrackRestController(private val service: TrackService) {

    @GetMapping
    fun overview(pageable: TablePageable): Page<TrackOverview> = service.findAll(pageable)

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