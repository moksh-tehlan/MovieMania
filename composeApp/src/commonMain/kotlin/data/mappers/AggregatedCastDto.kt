package data.mappers

import data.model.AggregatedResultDto
import domain.model.Cast

fun AggregatedResultDto.toAggregatedCast(): Cast {
    return Cast(
        id = id,
        name = name,
        profilePath = profilePath,
        character = roles.first().character,
    )
}