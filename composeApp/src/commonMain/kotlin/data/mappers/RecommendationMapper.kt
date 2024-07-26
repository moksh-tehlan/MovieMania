package data.mappers

import data.model.RecommendationResultDto
import domain.model.Media
import domain.model.MediaType

fun RecommendationResultDto.toMedia(): Media {
    return Media(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        mediaType = if(mediaType == "movie") MediaType.MOVIE else MediaType.TV
    )
}