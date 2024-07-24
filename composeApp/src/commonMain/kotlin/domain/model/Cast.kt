package domain.model

data class Cast(
    val id: Long,
    val name: String,
    val profilePath: String?,
    val character: String,
)