package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class TableRequestRepositoryImpl<T : Any, ID : Any>(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : TableRequestRepositoryCustom<T, ID> {

    override fun findAllCustom(rq: TablePageable): Page<T> {
        // Dummy-Implementierung: gibt eine leere Seite zurück
        // Hier solltest du die eigentliche Query generisch implementieren
        return PageImpl(emptyList<T>())
    }

}