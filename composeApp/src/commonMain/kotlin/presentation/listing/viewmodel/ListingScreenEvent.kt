package presentation.listing.viewmodel

sealed interface ListingScreenEvent {
    data class OnMovieClick(val movieId: String) : ListingScreenEvent
    data class OnTvShowClick(val tvId: String) : ListingScreenEvent
}