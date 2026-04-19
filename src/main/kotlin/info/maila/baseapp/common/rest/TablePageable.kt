package info.maila.baseapp.common.rest

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import kotlin.math.max
import kotlin.math.min

data class TablePageable(
    val offset: Long? = null,
    val limit: Int? = null,
    val sort: String? = null,
    val order: String? = null
) {

    fun pageable(): Pageable {
        val pageSize: Int = max(1, min(limit ?: LIMIT_DEFAULT, LIMIT_MAX))
        val pageNumber: Int = if (pageSize == 0) 0 else ((offset ?: 0L) / pageSize).toInt()
        val sort = if (sort == null) Sort.unsorted() else Sort.by(order.direction(), sort)
        return PageRequest.of(pageNumber, pageSize, sort)
    }

    private fun String?.direction(): Sort.Direction {
        if ("desc".equals(this, true)) return Sort.Direction.DESC
        return Sort.Direction.ASC
    }

    companion object {
        private const val LIMIT_DEFAULT = 20
        private const val LIMIT_MAX = 100
    }

}