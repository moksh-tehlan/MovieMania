package presentation.bookmark.viewmodel

import domain.model.Media
import utils.DataState

data class BookmarkState(
    val movies: DataState<List<Media>> = DataState.Loading,
    val tvShows: DataState<List<Media>> = DataState.Loading,
)
