package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePageable
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.data.jdbc.core.convert.DataAccessStrategy
import org.springframework.data.relational.core.query.CriteriaDefinition
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

interface TablePageableRepository<T : Any> {

    fun findAll(pageable: TablePageable, entityClass: KClass<T>): Page<T>

}

@Component
class TablePageableRepositoryImpl<T : Any>(
    private val jdbcAggregateTemplate: JdbcAggregateTemplate,
    private val dataAccessStrategy: DataAccessStrategy
) :
    TablePageableRepository<T> {

    private val logger = KotlinLogging.logger { }

    override fun findAll(pageable: TablePageable, entityClass: KClass<T>): Page<T> {
        val dataPageable = pageable.pageable()
        val query = buildQuery(dataPageable)
        val entities = dataAccessStrategy.findAll(query, entityClass.java, dataPageable)
        val total = dataAccessStrategy.count(entityClass.java) // TODO count with query
        return PageImpl(entities.toList(), dataPageable, total)
    }

    private fun buildQuery(pageable: Pageable): Query {
        val query = Query
            .query(CriteriaDefinition.empty())
            .with(pageable)
        logger.trace { query.debugString() }
        return query
    }

}

private fun Query.debugString(): String =
    listOf(
        "criteria=$criteria",
        "columns=$columns",
        "sort=$sort",
        "limit=$limit",
        offset
    ).joinToString(", ", prefix = "Query(", postfix = ")") { it.toString() }