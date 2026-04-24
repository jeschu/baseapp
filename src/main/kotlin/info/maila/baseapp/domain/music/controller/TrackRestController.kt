package info.maila.baseapp.domain.music

import info.maila.baseapp.common.model.TablePageable
import info.maila.baseapp.domain.music.service.TrackService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/tracks"])
class TrackRestController(private val service: TrackService) {

    @GetMapping
    fun overview(pageable: TablePageable) = service.findAll(pageable)

}