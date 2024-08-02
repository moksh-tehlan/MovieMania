package presentation.listing.viewmodel

import domain.model.Media
import utils.DataState

data class ListingScreenState(
    val isLoading: Boolean = false,
    val globalError: String? = null,
    val trendingMovies: DataState<List<Media>> = DataState.Loading,
    val topRatedMovies: DataState<List<Media>> = DataState.Loading,
    val popularMovies: DataState<List<Media>> = DataState.Loading,
    val popularTvShows: DataState<List<Media>> = DataState.Loading,
)
enum class ListingType{
    TOP_RATED_MOVIES,
    POPULAR_MOVIES,
    POPULAR_TV_SHOWS
}