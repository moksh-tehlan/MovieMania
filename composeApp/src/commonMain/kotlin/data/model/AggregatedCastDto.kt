package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AggregatedCastDto(
    val cast: List<AggregatedResultDto>,
    val id: Long,
)

@Serializable
data class AggregatedResultDto(
    val id: Long,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?,
    val roles: List<Role>,
)

@Serializable
data class Role(
    @SerialName("credit_id")
    val creditId: String,
    val character: String,
)