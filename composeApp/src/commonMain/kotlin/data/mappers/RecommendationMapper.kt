package data.mappers

import data.model.RecommendationResultDto
import domain.model.Recommendation

fun RecommendationResultDto.toRecommendation(): Recommendation {
    return Recommendation(
        adult,
        backdropPath,
        id,
        title,
        originalLanguage,
        originalTitle,
        overview,
        posterPath,
        mediaType,
        genreIds,
        popularity,
        releaseDate,
        video,
        voteAverage,
        voteCount
    )
}