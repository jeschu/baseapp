package info.maila.baseapp.common.rest

data class TablePage<T: Any>(
    val rows: Collection<T>,
    val total: Long? = null,
    val totalNonFiltered: Long? = null
)