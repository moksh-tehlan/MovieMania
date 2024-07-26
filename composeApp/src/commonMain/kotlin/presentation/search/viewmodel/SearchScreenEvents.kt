package presentation.search.viewmodel

import presentation.listing.viewmodel.ListingScreenEvent

sealed interface SearchScreenEvents {
    data class NavigateToMovieDetail(val movieId: String) : SearchScreenEvents
    data class NavigateToTvDetail(val tvId: String) : SearchScreenEvents
}