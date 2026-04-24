package info.maila.baseapp.domain.animal.repository

import info.maila.baseapp.database.repository.TablePageableRepository
import info.maila.baseapp.domain.animal.model.Animal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : TablePageableRepository<Animal>, CrudRepository<Animal, Long>