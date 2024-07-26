package data.mappers

import data.model.SearchResultDto
import domain.model.MediaType
import domain.model.Search

fun SearchResultDto.toSearch():Search{
    return Search(
        backdropPath = backdropPath,
        id = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        mediaType = if (mediaType == "movie") MediaType.MOVIE else MediaType.TV,
        adult = adult,
        originalLanguage = originalLanguage,
        genreIds = genreIds,
        popularity = popularity,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        name = name,
        originalName = originalName,
        firstAirDate = firstAirDate,
        originCountry = originCountry
    )
}