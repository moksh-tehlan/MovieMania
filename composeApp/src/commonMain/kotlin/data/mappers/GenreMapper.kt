package data.mappers

import data.model.GenreDto
import domain.model.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id, name
    )
}