package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//import com.squareup.moshi.Json

@Serializable
data class TvShowsDto(
    val page: Long,
    val results: List<TvResultDto>,
    @SerialName("total_pages")
    val totalPages: Long,
    @SerialName("total_results")
    val totalResults: Long,
)

@Serializable
data class TvResultDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String="",
    @SerialName("genre_ids")
    val genreIds: List<Long>,
    val id: Long,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String="",
    @SerialName("first_air_date")
    val firstAirDate: String,
    val name: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long,
)