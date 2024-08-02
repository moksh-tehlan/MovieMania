package data.mappers

import data.entity.MediaEntity
import data.model.MoviesResultDto
import domain.model.Media
import domain.model.MediaType

fun MoviesResultDto.toMedia(): Media {
    return Media(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        mediaType = MediaType.MOVIE
    )
}

fun MediaEntity.toMedia(): Media {
    return Media(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        isBookmarked = isBookmarked,
        mediaType = if (mediaType == "MOVIE") MediaType.MOVIE else MediaType.TV
    )
}