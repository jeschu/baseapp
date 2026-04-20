package info.maila.baseapp.database

import info.maila.baseapp.common.rest.TablePage
import info.maila.baseapp.common.rest.TablePageable
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.data.jdbc.core.convert.DataAccessStrategy
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

interface TablePageableRepository<T : Any> {

    fun findAll(pageable: TablePageable, entityClass: KClass<T>): TablePage<T>

}

@Component
class TablePageableRepositoryImpl<T : Any>(
    private val jdbcAggregateTemplate: JdbcAggregateTemplate,
    private val dataAccessStrategy: DataAccessStrategy
) :
    TablePageableRepository<T> {

    private val logger = KotlinLogging.logger { }

    override fun findAll(pageable: TablePageable, entityClass: KClass<T>): TablePage<T> {
        val dataPageable = pageable.pageable()
        val query = buildQuery(pageable)
        val entities = dataAccessStrategy.findAll(query, entityClass.java, dataPageable)
        val total = dataAccessStrategy.count(entityClass.java) // TODO count with query
        val totalNonFiltered = dataAccessStrategy.count(entityClass.java)
        return TablePage(entities.toList(), total, totalNonFiltered)
    }

    private fun buildQuery(pageable: TablePageable): Query {
        val criteria = Criteria.where("id").isNotNull
        val query = Query
            .query(criteria)
            .with(pageable.pageable())
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