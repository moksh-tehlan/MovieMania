package presentation.bookmark.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.dao.MediaDao
import domain.model.Media
import domain.model.MediaType
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
        }
    }

    init {
        getBookMark()
    }

    private fun getBookMark() {
        viewModelScope.launch {
            mediaDao.getAllMedia().collectLatest { mediaList ->
                println("MediaList: ${mediaList}")
                _bookmarkState.update {
                    it.copy(
                        movies = DataState.Success(data = mediaList.filter { it.mediaType == "MOVIE" }.also {
                            println("MediaMovieList: ${it}")
                        }
                            .map { mediaEntity ->
                                Media(
                                    id = mediaEntity.id,
                                    title = mediaEntity.title,
                                    posterPath = mediaEntity.posterPath,
                                    releaseDate = mediaEntity.releaseDate,
                                    mediaType = MediaType.MOVIE,
                                    overview = mediaEntity.overview,
                                    backdropPath = mediaEntity.backdropPath
                                )
                            }.also {
                                println("Movies: ${it}")
                            }),
                        tvShows = DataState.Success(data = mediaList.filter { it.mediaType == "TV" }
                            .also {
                                println("MediaTvList: ${it}")
                            }
                            .map { mediaEntity ->
                                Media(
                                    id = mediaEntity.id,
                                    title = mediaEntity.title,
                                    posterPath = mediaEntity.posterPath,
                                    releaseDate = mediaEntity.releaseDate,
                                    mediaType = MediaType.TV,
                                    overview = mediaEntity.overview,
                                    backdropPath = mediaEntity.backdropPath
                                )
                            }.also {
                                println("TvShows: ${it}")
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
}