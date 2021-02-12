package app.elite.mynote.data.network.model

data class RemoteNote<T>(
    val code: Int = 500,
    val error: String? = null,
    val data: T? = null
)