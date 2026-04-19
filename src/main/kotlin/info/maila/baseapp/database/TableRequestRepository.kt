package info.maila.baseapp.database

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

@NoRepositoryBean
interface TableRequestRepository<T : Any, ID : Any>
    : PagingAndSortingRepository<T, ID>, TableRequestRepositoryCustom<T, ID>