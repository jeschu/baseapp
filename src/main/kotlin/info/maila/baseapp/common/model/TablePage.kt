package info.maila.baseapp.common.model

import kotlin.time.Duration

data class TablePage<T : Any>(
    val rows: List<T>,
    val total: Long? = null,
    val totalNonFiltered: Long? = null,
    val queryRuntime: Duration? = null,
    val countRuntime: Duration? = null,
    val countTotalRuntime: Duration? = null
) {

    override fun toString(): String = "TablePage(" +
            "${rows.size} rows, " +
            "total=$total, " +
            "totalNonFiltered=$totalNonFiltered, " +
            "queryRuntime=$queryRuntime, " +
            "countRuntime=$countRuntime, " +
            "countTotalRuntime=$countTotalRuntime" +
            ")"

}