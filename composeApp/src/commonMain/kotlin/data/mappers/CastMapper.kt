package data.mappers

import data.model.CastResultDto
import domain.model.Cast

fun CastResultDto.toCast(): Cast {
    return Cast(
        id,
        name,
        profilePath,
        character,
    )
}