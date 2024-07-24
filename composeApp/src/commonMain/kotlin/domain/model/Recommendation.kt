package domain.model

data class Recommendation(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Long,
    val title: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val mediaType: String,
    val genreIds: List<Long>,
    val popularity: Double,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long,
)