package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page

class TableRequestRepositoryCustomImpl<T : Any, ID : Any> : TableRequestRepositoryCustom<T, ID> {

    override fun findAll(rq: TablePageable): Page<T> {
        TODO("Not yet implemented")
    }

}