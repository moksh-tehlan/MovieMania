package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val page: Long,
    val results: List<SearchResultDto>,
    @SerialName("total_pages")
    val totalPages: Long,
    @SerialName("total_results")
    val totalResults: Long,
)

@Serializable
data class SearchResultDto(
    @SerialName("backdrop_path")
    val backdropPath: String?=null,
    val id: Long,
    val title: String?=null,
    @SerialName("original_title")
    val originalTitle: String?=null,
    val overview: String?=null,
    @SerialName("poster_path")
    val posterPath: String?=null,
    @SerialName("media_type")
    val mediaType: String?=null,
    val adult: Boolean?=null,
    @SerialName("original_language")
    val originalLanguage: String?=null,
    @SerialName("genre_ids")
    val genreIds: List<Long>?=null,
    val popularity: Double?=null,
    @SerialName("release_date")
    val releaseDate: String?=null,
    val video: Boolean?=null,
    @SerialName("vote_average")
    val voteAverage: Double?=null,
    @SerialName("vote_count")
    val voteCount: Long?=null,
    val name: String?=null,
    @SerialName("original_name")
    val originalName: String?=null,
    @SerialName("first_air_date")
    val firstAirDate: String?=null,
    @SerialName("origin_country")
    val originCountry: List<String>?=null,
)
