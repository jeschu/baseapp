package info.maila.baseapp.database.repository

import info.maila.baseapp.common.rest.TablePage
import info.maila.baseapp.common.rest.TablePageable
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Component
import kotlin.reflect.KClass
import kotlin.time.measureTimedValue

interface TablePageableRepository<T : Any> {

    fun findAll(pageable: TablePageable, entityClass: KClass<T>): TablePage<T>

}

@Component
class TablePageableRepositoryImpl<T : Any>(
    private val jdbcAggregateTemplate: JdbcAggregateTemplate,
    private val entityFieldCache: EntityFieldCache
) :
    TablePageableRepository<T> {

    private val logger = KotlinLogging.logger { }

    override fun findAll(pageable: TablePageable, entityClass: KClass<T>): TablePage<T> {

        val criteria = buildCriteria(pageable, entityClass)
        var query = Query.query(criteria)
        query = query.limit(pageable.limit)
        query = query.offset(pageable.offset)
        if (pageable.sort != null) {
            query = query.sort(pageable.sort)
        }

        val (entities, entitiesRuntime) = measureTimedValue {
            jdbcAggregateTemplate.findAll(query, entityClass.java)
        }
        val (total, totalRuntime) = measureTimedValue {
            jdbcAggregateTemplate.count(Query.query(criteria), entityClass.java)
        }
        val (totalNonFiltered, totalNonFilteredRuntime) = measureTimedValue {
            jdbcAggregateTemplate.count(entityClass.java)
        }

        val page = TablePage(
            entities.toList(),
            total,
            totalNonFiltered,
            entitiesRuntime,
            totalRuntime,
            totalNonFilteredRuntime
        )
        logger.debug { "findAll<${entityClass.simpleName}>($pageable): ${query.debugString()} -> $page" }
        return page

    }

    private fun buildCriteria(pageable: TablePageable, entityClass: KClass<T>): Criteria {
        var criteria = Criteria.empty() //.where("id").isNotNull
        val search = pageable.search
        if (search != null) {
            val isNumber = search.isNumber()
            pageable.fields.forEach { field ->
                val entityField = entityFieldCache.get(entityClass, field)
                if (entityField.isString) {
                    criteria = criteria.or(entityField.column).like("%$search%")
                }
            }
        }
        return criteria
    }

}

private fun String.isNumber() =
    try {
        toDouble()
        true
    } catch (_: NumberFormatException) {
        false
    }

private fun Query.debugString(): String =
    listOf(
        "criteria=$criteria",
        "columns=$columns",
        "sort=$sort",
        "limit=$limit",
        offset
    ).joinToString(", ", prefix = "Query(", postfix = ")") { it.toString() }