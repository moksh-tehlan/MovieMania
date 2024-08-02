package data.mappers

import data.entity.MediaEntity
import domain.model.Media

fun Media.toMediaEntity(): MediaEntity {
    return MediaEntity(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        mediaType = mediaType.name,
        isBookmarked = isBookmarked
    )
}