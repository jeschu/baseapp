package info.maila.baseapp.domain.animal

import info.maila.baseapp.database.EntityNotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnimalService(private val repository: AnimalRepository) {

    fun findAll(pageable: Pageable) = repository.findAll(pageable)

    fun findById(id: Long): Animal = repository.findById(id)
        .orElseThrow { EntityNotFoundException(Animal::class, id) }

    fun update(id: Long, animal: Animal): Animal {
        require(id == animal.id) { "id missmatch" }
        requireNotNull(animal.version) { "version is null" }
        return findById(id)
            .updateWith(animal)
            .let(repository::save)
    }

    fun delete(id: Long) = repository.deleteById(id)

    fun save(animal: Animal): Animal {
        require(animal.id == null) { "id must be null for new entities" }
        require(animal.version == null) { "version must be null for new entities" }
        return repository.save(animal)
    }

}