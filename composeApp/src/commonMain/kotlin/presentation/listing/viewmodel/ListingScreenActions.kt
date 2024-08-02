package presentation.listing.viewmodel

import domain.model.Media
import domain.model.MediaType

sealed interface ListingScreenActions{
    data object GetListing:ListingScreenActions
    data class OnMovieClick(val movieId: String): ListingScreenActions
    data class OnTvShowClick(val tvId: String): ListingScreenActions
    data class OnCarousalClick(val id: String,val mediaType: MediaType): ListingScreenActions
    data class OnBookmark(val mediaItem: Media, val listingType: ListingType): ListingScreenActions
}