package presentation.listing.viewmodel

import domain.model.Movies
import domain.model.MoviesAndShows
import domain.model.TvShows

data class ListingScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val trendingMovies: List<MoviesAndShows> = emptyList(),
    val topRatedMovies: List<Movies> = emptyList(),
    val popularMovies: List<Movies> = emptyList(),
    val popularTvShows: List<TvShows> = emptyList()
)