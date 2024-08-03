package domain.model

data class Media(
    val backdropPath: String,
    val overview: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val id: Long,
    val mediaType: MediaType,
    val isBookmarked: Boolean = false,
)