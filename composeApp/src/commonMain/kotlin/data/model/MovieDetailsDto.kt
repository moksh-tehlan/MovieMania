package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String="",
    val genres: List<GenreDto>,
    val id: Long,
    @SerialName("imdb_id")
    val imdbId: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String="",
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val title: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long,
)

@Serializable
data class GenreDto(
    val id: Long,
    val name: String,
)

