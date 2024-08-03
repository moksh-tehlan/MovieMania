package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MoviesAndShowsDto(
    val page: Long,
    val results: List<MoviesAndShowsResultDto>,
    @SerialName("total_pages")
    val totalPages: Long,
    @SerialName("total_results")
    val totalResults: Long,
)

@Serializable
data class MoviesAndShowsResultDto(
    @SerialName("backdrop_path")
    val backdropPath: String="",
    val id: Long,
    val name: String?=null,
    @SerialName("original_name")
    val originalName: String?=null,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String="",
    @SerialName("media_type")
    val mediaType: String,
    val adult: Boolean,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("genre_ids")
    val genreIds: List<Long>,
    val popularity: Double,
    @SerialName("first_air_date")
    val firstAirDate: String?=null,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long,
    @SerialName("origin_country")
    val originCountry: List<String>?=null,
    val title: String?=null,
    @SerialName("original_title")
    val originalTitle: String?=null,
    @SerialName("release_date")
    val releaseDate: String?=null,
    val video: Boolean?=null,
)