package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface TableRequestRepositoryCustom<T : Any, ID : Any> {

    fun findAll(rq: TablePageable): Page<T>

}