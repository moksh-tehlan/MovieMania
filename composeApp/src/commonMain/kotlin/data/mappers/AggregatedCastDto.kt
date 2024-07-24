package data.mappers

import data.model.AggregatedCastDto
import data.model.AggregatedResultDto
import domain.model.AggregatedCast

fun AggregatedResultDto.toAggregatedCast(): AggregatedCast {
    return AggregatedCast(
        id = id,
        name = name,
        profilePath = profilePath,
        roles = roles.map { role ->
            AggregatedCast.Role(
                creditId = role.creditId,
                character = role.character
            )
        }
    )
}