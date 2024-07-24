package data.mappers

import data.model.TvResultDto
import domain.model.TvShows

fun TvResultDto.toTvShows(): TvShows {
    return TvShows(
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        id = id,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        adult = adult
    )
}
