package data.mappers

import data.model.TvResultDto
import domain.model.Media
import domain.model.MediaType

fun TvResultDto.toMedia(): Media {
    return Media(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        title = name,
        releaseDate = firstAirDate,
        mediaType = MediaType.TV
    )
}
