package data.mappers

import data.model.MoviesAndShowsResultDto
import domain.model.MediaType
import domain.model.MoviesAndShows

fun MoviesAndShowsResultDto.toMoviesAndShows(): MoviesAndShows {
    return MoviesAndShows(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        title = title,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        mediaType = if (mediaType == "movie") MediaType.MOVIE else MediaType.TV,
        popularity = popularity,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        name = name,
        originalName = originalName,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        originCountry = originCountry,
        voteCount = voteCount
    )
}