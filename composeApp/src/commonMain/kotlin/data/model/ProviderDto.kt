package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProviderDto(
    val id: Long,
    val results: ProviderResultDto
)

@Serializable
data class ProviderResultDto(
    @SerialName("IN")
    val india: In? = null
)

@Serializable
data class In(
    val link: String,
    @SerialName("flatrate")
    val flatRate: List<FlatRate>? = emptyList(),
)

@Serializable
data class FlatRate(
    @SerialName("logo_path")
    val logoPath: String,
    @SerialName("provider_id")
    val providerId: Long,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("display_priority")
    val displayPriority: Long,
)
