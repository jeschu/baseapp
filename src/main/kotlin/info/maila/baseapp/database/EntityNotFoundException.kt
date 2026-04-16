package info.maila.baseapp.database

import kotlin.reflect.KClass

class EntityNotFoundException(entityClass: KClass<*>, id: Long) :
    Exception("entity ${entityClass.simpleName}#$id not found")