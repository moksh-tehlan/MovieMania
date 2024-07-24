package data.mappers

import data.model.MoviesAndShowsResultDto
import domain.model.MoviesAndShows

fun MoviesAndShowsResultDto.toTrending(): MoviesAndShows {
    return MoviesAndShows(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        title = title,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        mediaType = if (mediaType == "movie") MoviesAndShows.MediaType.MOVIE else MoviesAndShows.MediaType.TV,
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