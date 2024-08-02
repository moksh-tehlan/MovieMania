package presentation.bookmark.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.dao.MediaDao
import data.mappers.toMedia
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.DataState

class BookmarkViewModel(
    private val mediaDao: MediaDao,
) : ViewModel() {

    private val _bookmarkState = MutableStateFlow(BookmarkState())
    val bookmarkState = _bookmarkState.asStateFlow()

    private val _bookmarkEvent = MutableSharedFlow<BookmarkEvent>()
    val bookmarkEvent = _bookmarkEvent.asSharedFlow()

    fun onAction(bookmarkActions: BookmarkActions) {
        when (bookmarkActions) {
            is BookmarkActions.OnMovieClick -> onMovieClick(bookmarkActions.movieId)
            is BookmarkActions.OnTvShowClick -> onTvShowClick(bookmarkActions.tvId)
            is BookmarkActions.RemoveBookmark -> onRemoveBookmark(bookmarkActions.id)
        }
    }

    init {
        getBookMark()
    }

    private fun getBookMark() {
        viewModelScope.launch {
            mediaDao.getAllMedia().collectLatest { mediaList ->
                _bookmarkState.update {
                    it.copy(
                        isEmpty = mediaList.isEmpty(),
                        movies = DataState.Success(data = mediaList.filter {movie-> movie.mediaType == "MOVIE" }
                            .map { mediaEntity ->
                                mediaEntity.toMedia()
                            }),
                        tvShows = DataState.Success(data = mediaList.filter {tvShow -> tvShow.mediaType == "TV" }
                            .map { mediaEntity ->
                                mediaEntity.toMedia()
                            })
                    )
                }
            }
        }
    }

    private fun onTvShowClick(tvId: String) {
        viewModelScope.launch {
            _bookmarkEvent.emit(BookmarkEvent.OnTvShowClick(tvId))
        }
    }

    private fun onMovieClick(movieId: String) {
        viewModelScope.launch {
            _bookmarkEvent.emit(BookmarkEvent.OnMovieClick(movieId))
        }
    }

    private fun onRemoveBookmark(id:Long){
        viewModelScope.launch {
            mediaDao.deleteMedia(id)
        }
    }
}