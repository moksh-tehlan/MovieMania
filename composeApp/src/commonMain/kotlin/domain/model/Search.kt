package domain.model

data class Search(
    val backdropPath: String?,
    val id: Long,
    val title: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val mediaType: String?,
    val adult: Boolean?,
    val originalLanguage: String?,
    val genreIds: List<Long>?,
    val popularity: Double?,
    val releaseDate: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Long?,
    val name: String?,
    val originalName: String?,
    val firstAirDate: String?,
    val originCountry: List<String>?,
)