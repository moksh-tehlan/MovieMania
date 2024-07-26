package domain.model

data class MoviesAndShows(
    val backdropPath: String,
    val id: Long,
    val name: String?,
    val originalName: String?,
    val overview: String,
    val posterPath: String,
    val mediaType: MediaType,
    val adult: Boolean,
    val originalLanguage: String,
    val genreIds: List<Long>,
    val popularity: Double,
    val firstAirDate: String?,
    val voteAverage: Double,
    val voteCount: Long,
    val originCountry: List<String>?,
    val title: String?,
    val originalTitle: String?,
    val releaseDate: String?,
    val video: Boolean?,
)