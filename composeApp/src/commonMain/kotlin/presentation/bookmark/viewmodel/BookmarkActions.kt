package presentation.bookmark.viewmodel

sealed interface BookmarkActions {
    data class OnMovieClick(val movieId: String): BookmarkActions
    data class OnTvShowClick(val tvId: String): BookmarkActions
    data class RemoveBookmark(val id: Long): BookmarkActions
}