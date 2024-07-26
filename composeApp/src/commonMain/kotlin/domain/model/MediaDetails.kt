package domain.model

data class MediaDetails(
    val backdropPath: String,
    val overview: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Long,
    val popularity: Double,
    val runtime: String?,
    val id: Long,
    val mediaType: MediaType,
    val genres: List<Genre>,
    val seasons : List<TvShowSeason> = emptyList(),
)