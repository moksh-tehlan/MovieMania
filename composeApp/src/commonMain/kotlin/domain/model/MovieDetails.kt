package domain.model

import utils.Formatter


data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String,
//    val budget: Long,
    val genres: List<Genre>,
//    val homepage: String,
    val id: Long,
    val imdbId: String,
//    val originCountry: List<String>,
//    val originalLanguage: String,
//    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
//    val productionCompanies: List<ProductionCompanyDto>,
//    val productionCountries: List<ProductionCountryDto>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
//    val spokenLanguages: List<SpokenLanguageDto>,
//    val status: String,
//    val tagline: String,
    val title: String,
//    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long,
) {

    val formattedRunTime: String
        get() = Formatter.formatMinutesToHoursAndMinutes(runtime)

    data class BelongsToCollection(
        val id: Long,
        val name: String,
        val posterPath: String,
        val backdropPath: String,
    )



    data class ProductionCompany(
        val id: Long,
        val logoPath: String,
        val name: String,
        val originCountry: String,
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String,
    )

    data class SpokenLanguage(
        val englishName: String,
        val iso6391: String,
        val name: String,
    )
}
