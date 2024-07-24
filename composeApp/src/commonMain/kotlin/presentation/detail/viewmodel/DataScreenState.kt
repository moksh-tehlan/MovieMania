package presentation.detail.viewmodel

import domain.model.MovieDetails

data class DataScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movieDetail: MovieDetails? = null
)
