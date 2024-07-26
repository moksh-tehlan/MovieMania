package data.mappers

import data.model.SearchResultDto
import domain.model.Media
import domain.model.MediaType

fun SearchResultDto.toMedia(): Media {
    return Media(
        backdropPath = backdropPath ?: "N/A",
        id = id,
        title = name ?: title ?: "N/A",
        overview = overview ?: "N/A",
        posterPath = posterPath,
        mediaType = if (mediaType == "movie") MediaType.MOVIE else MediaType.TV,
        releaseDate = releaseDate ?: firstAirDate ?: "N/A"
    )
}