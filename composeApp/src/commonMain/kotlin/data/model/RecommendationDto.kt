package data.model

//import com.squareup.moshi.Json

data class RecommendationDto(
    val page: Long,
    val results: List<RecommendationResultDto>,
//    @Json(name = "total_pages")
    val totalPages: Long,
//    @Json(name = "total_results")
    val totalResults: Long,
)

data class RecommendationResultDto(
    val adult: Boolean,
//    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val id: Long,
    val title: String,
//    @Json(name = "original_language")
    val originalLanguage: String,
//    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
//    @Json(name = "poster_path")
    val posterPath: String?,
//    @Json(name = "media_type")
    val mediaType: String,
//    @Json(name = "genre_ids")
    val genreIds: List<Long>,
    val popularity: Double,
//    @Json(name = "release_date")
    val releaseDate: String,
    val video: Boolean,
//    @Json(name = "vote_average")
    val voteAverage: Double,
//    @Json(name = "vote_count")
    val voteCount: Long,
)