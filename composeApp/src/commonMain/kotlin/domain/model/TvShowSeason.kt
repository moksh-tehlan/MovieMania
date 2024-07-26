package domain.model

data class TvShowSeason(
    val episodeCount: Long,
    val name: String,
    val posterPath: String? = null,
    val seasonNumber: Long,
)