package data.mappers

import data.model.BelongsToCollectionDto
import data.model.GenreDto
import data.model.MovieDetailsDto
import data.model.ProductionCompanyDto
import data.model.ProductionCountryDto
import data.model.SpokenLanguageDto
import domain.model.Genre
import domain.model.MovieDetails

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = adult,
        backdropPath = backdropPath,
//        budget = budget,
        genres = genres.map { it.toGenre() },
//        homepage = homepage,
        id = id,
        imdbId = imdbId,
//        originalLanguage = originalLanguage,
//        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
//        productionCompanies = productionCompanies.map { it.toProductionCompany() },
//        productionCountries = productionCountries.map { it.toProductionCountry() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
//        spokenLanguages = spokenLanguages.map { it.toSpokenLanguage() },
//        status = status,
//        tagline = tagline,
        title = title,
//        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
//        originCountry = originCountry
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(
        id, name
    )
}

fun ProductionCompanyDto.toProductionCompany(): MovieDetails.ProductionCompany {
    return MovieDetails.ProductionCompany(
        id, logoPath, name, originCountry
    )
}

fun ProductionCountryDto.toProductionCountry(): MovieDetails.ProductionCountry {
    return MovieDetails.ProductionCountry(
        iso31661, name
    )
}

fun SpokenLanguageDto.toSpokenLanguage(): MovieDetails.SpokenLanguage {
    return MovieDetails.SpokenLanguage(
        englishName, iso6391, name
    )
}

fun BelongsToCollectionDto.toBelongToCollection(): MovieDetails.BelongsToCollection {
    return MovieDetails.BelongsToCollection(
        id, name, posterPath, backdropPath
    )
}