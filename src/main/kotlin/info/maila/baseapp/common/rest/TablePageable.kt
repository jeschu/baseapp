package info.maila.baseapp.common.rest

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.Direction.DESC
import kotlin.math.min

data class TablePageable(
    val offset: Long? = null,
    val limit: Int? = null,
    val sort: String? = null,
    val order: String? = null
) : Pageable {

    override fun getPageNumber(): Int = ((offset ?: 0L) / (limit ?: LIMIT_DEFAULT)).toInt()

    override fun getPageSize(): Int = min(limit ?: LIMIT_DEFAULT, LIMIT_MAXIMUM)

    override fun getOffset(): Long = offset ?: 0L

    override fun getSort(): Sort {
        if (sort == null) return Sort.unsorted()
        return Sort.by(Sort.Order(order.asSortDirection(), sort))
    }

    override fun next(): Pageable = copy(offset = (offset ?: 0L) + (limit ?: LIMIT_DEFAULT))


    override fun previousOrFirst(): Pageable =
        copy(offset = (offset ?: 0L) - (limit ?: LIMIT_DEFAULT))

    override fun first(): Pageable = copy(offset = 0)

    override fun withPage(pageNumber: Int): Pageable =
        copy(offset = (pageNumber * (limit ?: LIMIT_DEFAULT)).toLong())

    override fun hasPrevious(): Boolean = (offset ?: 0) > 0

    companion object {
        const val LIMIT_DEFAULT = 10
        const val LIMIT_MAXIMUM = 100
    }

    private fun String?.asSortDirection(): Sort.Direction =
        if ("desc".equals(this, true)) DESC else ASC

}