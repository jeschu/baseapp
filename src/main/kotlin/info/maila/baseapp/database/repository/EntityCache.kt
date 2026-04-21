package info.maila.baseapp.database.repository

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.mapping.PersistentProperty
import org.springframework.data.relational.core.mapping.RelationalMappingContext
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class EntityCache(private val mappingContext: RelationalMappingContext) {

    private val logger = KotlinLogging.logger { }

    private val tableCache: MutableMap<KClass<*>, RelationalPersistentEntity<*>> = mutableMapOf()
    private val fieldCache: MutableMap<KClass<*>, MutableMap<String, EntityField?>> = mutableMapOf()

    fun get(entityClass: KClass<*>) = tableCache
        .computeIfAbsent(entityClass) {
            mappingContext.getPersistentEntity(it.java)
                ?: throw IllegalStateException("No persistent entity found for class ${entityClass.qualifiedName}")

        }

    fun get(entityClass: KClass<*>, field: String): EntityField? = fieldCache
        .computeIfAbsent(entityClass) { mutableMapOf() }
        .computeIfAbsent(field) {
            val entityField = entityField(entityClass, field)
            logger.trace { "reflected ${entityClass.simpleName}.$field -> ${entityField?.let { "column='${it.column}', isString=${it.isString}" } ?: "null"}" }
            entityField
        }

    private fun entityField(entityClass: KClass<*>, field: String): EntityField? =
        entityClass.getPersistentProperty(field)?.let { persistentProperty ->
            EntityField(
                entityClass = entityClass,
                field = field,
                column = persistentProperty.columnName.reference,
                isString = persistentProperty.isString()
            )
        }

    private fun KClass<*>.getPersistentProperty(field: String): RelationalPersistentProperty? =
        get(this).getPersistentProperty(field)

    private fun PersistentProperty<*>.isString() = rawType.isAssignableFrom(String::class.java)

}

data class EntityField(
    val entityClass: KClass<*>,
    val field: String,
    val column: String,
    val isString: Boolean
)