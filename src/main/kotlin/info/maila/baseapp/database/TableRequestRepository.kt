package info.maila.baseapp.database

import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface TableRequestRepository<T : Any, ID : Any> : TableRequestRepositoryCustom<T>