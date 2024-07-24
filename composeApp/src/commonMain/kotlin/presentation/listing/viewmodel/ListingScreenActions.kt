package presentation.listing.viewmodel

sealed interface ListingScreenActions{
    data class OnMovieClick(val movieId: String): ListingScreenActions
    data class OnTvShowClick(val tvId: String): ListingScreenActions
    data class OnCarousalClick(val pageNo: Int): ListingScreenActions
}