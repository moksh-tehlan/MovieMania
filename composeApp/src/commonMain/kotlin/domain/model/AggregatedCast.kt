package domain.model

import kotlinx.serialization.SerialName

data class AggregatedCast(
    val id: Long,
    val name: String,
    val profilePath: String?,
    val roles: List<Role>,
) {
    data class Role(
        val creditId: String,
        val character: String,
    )
}