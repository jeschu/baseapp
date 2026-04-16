package info.maila.baseapp.domain.animal

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : PagingAndSortingRepository<Animal, Long>, CrudRepository<Animal, Long>