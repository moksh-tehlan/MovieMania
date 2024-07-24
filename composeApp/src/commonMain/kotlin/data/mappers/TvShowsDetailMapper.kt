package data.mappers

import data.model.TvShowDetailsDto
import data.model.TvShowSeasonDto
import domain.model.TvShowDetail

fun TvShowDetailsDto.toTvShowDetail() : TvShowDetail{
    return TvShowDetail(
        adult = adult,
        backdropPath = backdropPath,
        episodeRunTime = episodeRunTime,
        firstAirDate = firstAirDate,
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = id,
        inProduction = inProduction,
        languages = languages,
        lastAirDate = lastAirDate,
        name = name,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        seasons = seasons.map { it.toTvShowSeason() },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}

fun TvShowSeasonDto.toTvShowSeason() : TvShowDetail.TvShowSeason{
    return TvShowDetail.TvShowSeason(
        airDate = airDate,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        seasonNumber = seasonNumber,
        voteAverage = voteAverage,
    )
}