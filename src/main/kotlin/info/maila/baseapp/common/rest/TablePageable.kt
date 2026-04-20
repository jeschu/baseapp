package info.maila.baseapp.common.rest

import org.springframework.data.domain.Sort
import kotlin.math.min

class TablePageable(
    offset: Long? = null,
    limit: Int? = null,
    sort: String? = null,
    val order: String? = null,
    val search: String? = null,
    visibleFields: List<String>? = null,
) {

    val offset: Long = offset ?: 0L
    val limit: Int = min(limit ?: LIMIT_DEFAULT, LIMIT_MAX)
    val sort: Sort? = sort?.let {
        val direction = if ("desc".equals(order, true)) Sort.Direction.DESC else Sort.Direction.ASC
        Sort.by(direction, it)
    }
    val fields: List<String> = visibleFields
        ?.filterNot { it.matches(FILTER_VISIBLE_FIELDS_REGEX) }
        ?: emptyList()
    val actionsVisible = visibleFields?.contains(FILTER_VISIBLE_FIELDS_ACTIONS) ?: false

    override fun toString(): String = "TablePageable(" +
            "offset=$offset, " +
            "limit=$limit, " +
            "sort=$sort, " +
            "order=$order, " +
            "search=$search, " +
            "fields=$fields" +
            ")"

    /**
    fun pageable(): Pageable {
    val pageSize: Int = max(1, min(limit ?: LIMIT_DEFAULT, LIMIT_MAX))
    val pageNumber: Int = if (pageSize == 0) 0 else ((offset ?: 0L) / pageSize).toInt()
    val sort = if (sort == null) Sort.unsorted() else Sort.by(order.direction(), sort)
    return PageRequest.of(pageNumber, pageSize, sort)
    }
     */

    companion object {
        private const val LIMIT_DEFAULT = 20
        private const val LIMIT_MAX = 100
        private val FILTER_VISIBLE_FIELDS_REGEX = Regex("\\[_.*_]")
        private const val FILTER_VISIBLE_FIELDS_ACTIONS = "[_actions_]"
    }

}