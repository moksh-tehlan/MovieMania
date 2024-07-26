package data.mappers

import data.model.TvShowDetailsDto
import data.model.TvShowSeasonDto
import domain.model.MediaDetails
import domain.model.MediaType
import domain.model.TvShowSeason

fun TvShowDetailsDto.toTvShowDetail(): MediaDetails {
    return MediaDetails(
        backdropPath = backdropPath,
        genres = genres.map { it.toGenre() },
        id = id,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        seasons = seasons.map { it.toTvShowSeason() },
        voteAverage = voteAverage,
        voteCount = voteCount,
        mediaType = MediaType.TV,
        title = name,
        releaseDate = firstAirDate,
        runtime = null,
    )
}

fun TvShowSeasonDto.toTvShowSeason(): TvShowSeason {
    return TvShowSeason(
        episodeCount = episodeCount,
        name = name,
        posterPath = posterPath,
        seasonNumber = seasonNumber,
    )
}