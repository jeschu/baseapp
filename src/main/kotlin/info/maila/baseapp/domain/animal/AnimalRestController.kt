package info.maila.baseapp.domain.animal

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/animals"])
class AnimalRestController(private val service: AnimalService) {

    @GetMapping
    operator fun get(pageable: Pageable) = service.findAll(pageable)

}