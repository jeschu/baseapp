package info.maila.baseapp.domain.music

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["/tracks"])

class TrackContoller(private val service: TrackService) {

    @GetMapping
    fun overview(): String = "music/music-overview"

}