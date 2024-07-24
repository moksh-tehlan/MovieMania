package domain.model

data class TvShowDetail(
    val adult: Boolean,
    val backdropPath: String,
    val episodeRunTime: List<Long>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Long,
    val numberOfSeasons: Long,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<TvShowSeason>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Long,
){
    data class TvShowSeason(
        val airDate: String?,
        val episodeCount: Long,
        val id: Long,
        val name: String,
        val overview: String,
        val posterPath: String?,
        val seasonNumber: Long,
        val voteAverage: Double,
    )
}