package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CastDto(
    val id: Long,
    val cast: List<CastResultDto>,
)

@Serializable
data class CastResultDto(
    val id: Long,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?,
    val character: String,
)