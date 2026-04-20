package info.maila.baseapp.database.repository

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.mapping.PersistentProperty
import org.springframework.data.relational.core.mapping.RelationalMappingContext
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class EntityCache(private val mappingContext: RelationalMappingContext) {

    private val logger = KotlinLogging.logger { }

    private val tableCache: MutableMap<KClass<*>, RelationalPersistentEntity<*>> = mutableMapOf()
    private val fieldCache: MutableMap<KClass<*>, MutableMap<String, EntityField>> = mutableMapOf()

    fun get(entityClass: KClass<*>) = tableCache
        .computeIfAbsent(entityClass) {
            mappingContext.getPersistentEntity(it.java)
                ?: throw IllegalStateException("No persistent entity found for class ${entityClass.qualifiedName}")

        }

    fun get(entityClass: KClass<*>, field: String): EntityField = fieldCache
        .computeIfAbsent(entityClass) { mutableMapOf() }
        .computeIfAbsent(field) {
            val entityField = entityField(entityClass, field)
            logger.trace { "reflected ${entityClass.simpleName}.$field -> column='${entityField.column}', isString=${entityField.isString}" }
            entityField
        }

    private fun entityField(entityClass: KClass<*>, field: String): EntityField {
        val persistentProperty = entityClass.getPersistentProperty(field)
        val column: String = persistentProperty.columnName.reference
        val isString: Boolean = persistentProperty.isString()
        return EntityField(entityClass, field, column, isString)
    }

    private fun KClass<*>.getPersistentProperty(field: String) = get(this)
        .let {
            it.getPersistentProperty(field)
                ?: throw IllegalStateException("No persistent property found for field '$field' in class $qualifiedName")
        }

    private fun PersistentProperty<*>.isString() = rawType.isAssignableFrom(String::class.java)

}

data class EntityField(
    val entityClass: KClass<*>,
    val field: String,
    val column: String,
    val isString: Boolean
)