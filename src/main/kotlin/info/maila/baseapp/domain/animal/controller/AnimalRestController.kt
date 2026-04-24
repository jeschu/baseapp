package info.maila.baseapp.domain.animal.controller

import info.maila.baseapp.common.model.TablePageable
import info.maila.baseapp.domain.animal.service.AnimalService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/animals"])
class AnimalRestController(private val service: AnimalService) {

    @GetMapping
    fun overview(pageable: TablePageable) = service.findAll(pageable)

}