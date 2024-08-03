package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDetailsDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String="",
    @SerialName("episode_run_time")
    val episodeRunTime: List<Long>,
    @SerialName("first_air_date")
    val firstAirDate: String,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Long,
    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String,
    val name: String,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Long,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Long,
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
    val seasons: List<TvShowSeasonDto>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long,
)

@Serializable
data class TvShowSeasonDto(
    @SerialName("air_date")
    val airDate: String?=null,
    @SerialName("episode_count")
    val episodeCount: Long,
    val id: Long,
    val name: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?=null,
    @SerialName("season_number")
    val seasonNumber: Long,
    @SerialName("vote_average")
    val voteAverage: Double,
)

