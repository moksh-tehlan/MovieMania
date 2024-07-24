package domain.model

import utils.Formatter

data class TvShows(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Long>,
    val id: Long,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
    val voteCount: Long,
)