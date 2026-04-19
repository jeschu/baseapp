package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class TableRequestRepositoryCustomImpl<T : Any, ID : Any>(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : TableRequestRepositoryCustom<T, ID> {

    override fun findAll(rq: TablePageable): Page<T> {
        // Dummy-Implementierung: gibt eine leere Seite zurück
        // Hier solltest du die eigentliche Query generisch implementieren
        return PageImpl(emptyList<T>())
    }

}