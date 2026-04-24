package info.maila.baseapp.domain.animal.service

import info.maila.baseapp.common.model.TablePageable
import info.maila.baseapp.database.EntityNotFoundException
import info.maila.baseapp.domain.animal.model.Animal
import info.maila.baseapp.domain.animal.repository.AnimalRepository
import org.springframework.stereotype.Service

@Service
class AnimalService(private val repository: AnimalRepository) {

    fun findAll(pageable: TablePageable) = repository.findAll(pageable, Animal::class)

    fun findById(id: Long): Animal = repository.findById(id)
        .orElseThrow { EntityNotFoundException(Animal::class, id) }

    fun save(animal: Animal): Animal {
        require(animal.id == null) { "id must be null for new entities" }
        require(animal.dbVersion == null) { "version must be null for new entities" }
        return repository.save(animal)
    }

    fun update(id: Long, animal: Animal): Animal {
        require(id == animal.id) { "id missmatch" }
        requireNotNull(animal.dbVersion) { "version is null" }
        return findById(id)
            .updateWith(animal)
            .let(repository::save)
    }

    fun delete(id: Long) = repository.deleteById(id)

}