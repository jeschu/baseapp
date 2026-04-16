package info.maila.baseapp.common.rest

data class RestResult(
    val success: Boolean = true,
    val location: String? = null,
    val reload: String? = null,
    val error: ErrorResult? = null
)

data class ErrorResult(val level: String, val header: String, val message: String? = null)

@Suppress("unused")
enum class ErrorLevel() {
    ERROR, WARN, INFO;

    val level = name.lowercase()
}