package info.maila.baseapp.domain.animal.controller

import info.maila.baseapp.common.rest.RestResult
import info.maila.baseapp.config.security.Role.RESOURCE_BASEAPP_EDITOR
import info.maila.baseapp.config.security.Role.RESOURCE_BASEAPP_PUBLIC
import info.maila.baseapp.domain.animal.model.Animal
import info.maila.baseapp.domain.animal.service.AnimalService
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["/animals"])
class AnimalController(private val service: AnimalService) {

    private val logger = KotlinLogging.logger { }

    @PreAuthorize("hasRole('${RESOURCE_BASEAPP_PUBLIC}')")
    @GetMapping
    fun overview(): String = "animals-overview"

    @PreAuthorize("hasRole('${RESOURCE_BASEAPP_EDITOR}')")
    @GetMapping(path = ["/{id}"])
    fun edit(
        @PathVariable id: Long, model: Model
    ): String {
        model.addAttribute(service.findById(id))
        return "animals-edit"
    }

    @PreAuthorize("hasRole('${RESOURCE_BASEAPP_EDITOR}')")
    @GetMapping(path = ["/new"])
    fun create(model: Model): String {
        model.addAttribute(Animal())
        return "animals-edit"
    }

    @PreAuthorize("hasRole('${RESOURCE_BASEAPP_EDITOR}')")
    @PostMapping(path = ["/new"])
    fun save(
        @Valid animal: Animal, b: BindingResult
    ): String {
        logger.debug { "save() - ${b.errorCount} errors" }
        if (b.hasErrors()) return "animals-edit"

        val saved = service.save(animal)
        return "redirect:/animals/${saved.id}"
    }

    @PreAuthorize("hasRole('${RESOURCE_BASEAPP_EDITOR}')")
    @PostMapping(path = ["/{id}"])
    fun update(
        @PathVariable id: Long,
        @Valid animal: Animal, b: BindingResult,
    ): String {
        logger.debug { "update($id) - ${b.errorCount} errors" }
        if (b.hasErrors()) return "animals-edit"

        service.update(id, animal)
        return "redirect:/animals/${id}"
    }

    @PreAuthorize("hasRole('${RESOURCE_BASEAPP_EDITOR}')")
    @DeleteMapping(path = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun delete(@PathVariable id: Long) = service.delete(id).let {
        RestResult(reload = "animals")
    }

}