package presentation.detail.viewmodel

import domain.model.Media
import domain.model.MediaDetails

sealed interface DetailScreenActions {
    data class OnBookmarkMediaDetails(val mediaDetails: MediaDetails) : DetailScreenActions
    data class OnBookmarkMedia(val media: Media) : DetailScreenActions
}