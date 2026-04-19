package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Page.empty

interface TablePageableRepository<T : Any> {

    fun findAll(pageable: TablePageable): Page<T>

}

class TablePageableRepositoryImpl<T : Any> : TablePageableRepository<T> {

    override fun findAll(pageable: TablePageable): Page<T> {
        return empty()
    }

}