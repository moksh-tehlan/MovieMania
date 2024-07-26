package data.mappers

import data.model.MoviesAndShowsResultDto
import domain.model.Media
import domain.model.MediaType

fun MoviesAndShowsResultDto.toMoviesAndShows(): Media {
    return Media(
        id = id,
        backdropPath = backdropPath,
        title = title ?: name ?: "N/A",
        overview = overview,
        posterPath = posterPath,
        mediaType = if (mediaType == "movie") MediaType.MOVIE else MediaType.TV,
        releaseDate = releaseDate ?: firstAirDate ?: "N/A"
    )
}