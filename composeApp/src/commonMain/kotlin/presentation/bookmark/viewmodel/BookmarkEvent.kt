package presentation.bookmark.viewmodel

sealed interface BookmarkEvent {
    data class OnMovieClick(val movieId: String) : BookmarkEvent
    data class OnTvShowClick(val tvId: String) : BookmarkEvent
}