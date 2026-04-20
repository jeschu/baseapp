package info.maila.baseapp.database.repository

import info.maila.baseapp.common.model.TablePage
import info.maila.baseapp.common.model.TablePageable
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Sort
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.data.jdbc.core.convert.JdbcConverter
import org.springframework.data.relational.domain.RowDocument
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlin.time.TimedValue
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

interface TablePageableRepository<T : Any> {

    fun findAll(pageable: TablePageable, entityClass: KClass<T>): TablePage<T>

}

@Component
class TablePageableRepositoryImpl<T : Any>(
    private val jdbcAggregateTemplate: JdbcAggregateTemplate,
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val jdbcConverter: JdbcConverter,
    private val entityCache: EntityCache
) : TablePageableRepository<T> {

    private val logger = KotlinLogging.logger { }

    override fun findAll(pageable: TablePageable, entityClass: KClass<T>): TablePage<T> {

        val (sql, countSql) = buildSql(pageable, entityClass)
        logger.debug { "findAll<${entityClass.simpleName}> SQL: $sql" }
        val (entities, queryRuntime) = query(sql, entityClass)
        val (total, countRuntime) = count(countSql)
        val (totalNonFiltered, countTotalRuntime) = countAll(entityClass)
        val page = TablePage(
            rows = entities, queryRuntime = queryRuntime,
            total = total, countRuntime = countRuntime,
            totalNonFiltered = totalNonFiltered, countTotalRuntime = countTotalRuntime
        )
        logger.trace { "findAll<${entityClass.simpleName}> $page <- $pageable" }
        return page
    }

    private fun buildSql(
        pageable: TablePageable,
        entityClass: KClass<T>
    ): Pair<String, String> {
        val tableName = entityCache.get(entityClass).tableName.reference
        val fields = "*"
        val whereClauses = mutableListOf<String>()
        val search = pageable.search
        if (search != null) {
            val searchLower = search.lowercase()
            pageable.fields
                .filter { field -> entityCache.get(entityClass, field).isString }
                .forEach { field ->
                    whereClauses += "LOWER(\"$field\") LIKE '%$searchLower%'"
                }
        }
        val whereClause =
            if (whereClauses.isEmpty()) ""
            else whereClauses.joinToString(separator = " OR ", prefix = " WHERE ") { "($it)" }
        val sort = pageable.sort
        val orderClause = if (sort != null && sort.isSorted) {
            val sortList: List<Sort.Order> = sort.toList()
            sortList.joinToString(separator = ", ", prefix = " ORDER BY ") {
                "${it.property} ${it.direction.name}"
            }
        } else ""
        val sql = "" +
                "SELECT $fields " +
                "FROM \"$tableName\"" +
                "$whereClause " +
                "$orderClause " +
                "OFFSET ${pageable.offset} " +
                "LIMIT ${pageable.limit}"
        val countSql = "" +
                "SELECT COUNT (*) " +
                "FROM \"$tableName\"" +
                whereClause
        return Pair(sql, countSql)
    }

    private fun query(sql: String, entityClass: KClass<T>): Pair<List<T>, Duration> {
        val entities = mutableListOf<T>()
        val runtime = measureTime {
            namedParameterJdbcTemplate.query(sql, paramMap) { rs: ResultSet ->
                val metaData = rs.metaData
                entities += jdbcConverter.read(
                    entityClass.java,
                    RowDocument(
                        (1..metaData.columnCount)
                            .mapNotNull(metaData::getColumnName)
                            .associateWith(rs::getObject)
                    )
                )
            }
        }
        return Pair(entities, runtime)
    }

    private fun count(sql: String): Pair<Long, Duration> {
        var total: Long = 0
        val runtime = measureTime {
            namedParameterJdbcTemplate.query(sql, paramMap) { rs: ResultSet ->
                total = rs.getLong(1)
            }
        }
        return Pair(total, runtime)
    }

    private fun countAll(entityClass: KClass<T>): TimedValue<Long> = measureTimedValue {
        jdbcAggregateTemplate.count(entityClass.java)
    }

    val paramMap = emptyMap<String, Any>()

}