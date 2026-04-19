package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page

interface TableRequestRepositoryCustom<T : Any, ID : Any> {

    fun findAllCustom(rq: TablePageable): Page<T>

}