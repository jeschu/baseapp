package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Repository

@Repository
class TableRequestRepositoryImpl<T : Any> : TableRequestRepositoryCustom<T> {
    override fun search(pageable: TablePageable): Page<T> {
        // Leere Implementierung, kann später angepasst werden
        return PageImpl(emptyList<T>(), pageable, 0)
    }
}