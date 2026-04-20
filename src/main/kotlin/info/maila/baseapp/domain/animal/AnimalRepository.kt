package info.maila.baseapp.domain.animal

import info.maila.baseapp.database.repository.TablePageableRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : TablePageableRepository<Animal>, CrudRepository<Animal, Long>