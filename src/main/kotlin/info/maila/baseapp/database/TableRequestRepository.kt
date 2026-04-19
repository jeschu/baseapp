package info.maila.baseapp.database

import org.springframework.data.repository.PagingAndSortingRepository

interface TableRequestRepository<T : Any, ID : Any>
    : PagingAndSortingRepository<T, ID>, TableRequestRepositoryCustom<T, ID>