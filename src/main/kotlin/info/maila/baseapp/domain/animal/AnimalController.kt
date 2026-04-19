package info.maila.baseapp.domain.animal

import info.maila.baseapp.common.rest.RestResult
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["/animals"])
class AnimalController(private val service: AnimalService) {

    private val logger = KotlinLogging.logger { }

    @GetMapping
    fun overview(): String = "animals-overview"

    @GetMapping(path = ["/{id}"])
    fun edit(
        @PathVariable id: Long, model: Model
    ): String {
        val animal: Animal = service.findById(id)
        model.addAttribute("animal", animal)
        return "animals-edit"
    }

    @GetMapping(path = ["/new"])
    fun create(model: Model): String {
        model.addAttribute("animal", Animal())
        logger.debug { "model: $model" }
        return "animals-edit"
    }

    @PostMapping(path = ["/new"])
    fun save(
        @Valid animal: Animal, b: BindingResult
    ): String {
        logger.debug { "save() - ${b.errorCount} errors" }
        if (b.hasErrors()) return "animals-edit"

        val saved = service.save(animal)
        return "redirect:/animals/${saved.id}"
    }

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

    @DeleteMapping(path = ["/{id}"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun delete(@PathVariable id: Long) = service.delete(id).let {
        RestResult(reload = "animals")
    }

}