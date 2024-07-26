package data.mappers

import data.model.MovieDetailsDto
import domain.model.MediaDetails
import domain.model.MediaType
import utils.Formatter

fun MovieDetailsDto.toMediaDetails(): MediaDetails {
    return MediaDetails(
        backdropPath = backdropPath,
        genres = genres.map { it.toGenre() },
        id = id,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
        mediaType = MediaType.TV,
        runtime = Formatter.formatMinutesToHoursAndMinutes(runtime),
    )
}

