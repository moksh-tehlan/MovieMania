package presentation.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.dao.MediaDao
import data.entity.MediaEntity
import domain.model.Media
import domain.model.MediaType
import domain.repository.MovieRepository
import domain.utils.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.DataState

class ListingScreenViewModel(
    private val movieRepository: MovieRepository,
    private val movieDao: MediaDao,
) : ViewModel() {

    private val _listingScreenState = MutableStateFlow(ListingScreenState())
    val listingScreenState = _listingScreenState.asStateFlow()

    private val _listingScreenEvents = MutableSharedFlow<ListingScreenEvent>()
    val listingScreenEvents = _listingScreenEvents.asSharedFlow()

    init {
        getTrendingMoviesAndShows()
        getPopularMovies()
        getPopularTvShows()
        getTopRatedMovies()
    }

    fun onAction(listingScreenAction: ListingScreenActions) {
        when (listingScreenAction) {
            is ListingScreenActions.OnMovieClick -> onMovieClick(listingScreenAction.movieId)
            is ListingScreenActions.OnCarousalClick -> onCarousalClick(
                listingScreenAction.id,
                listingScreenAction.mediaType
            )

            is ListingScreenActions.OnTvShowClick -> onTvShowClick(listingScreenAction.tvId)
        }
    }

    private fun onTvShowClick(tvId: String) {
        viewModelScope.launch { _listingScreenEvents.emit(ListingScreenEvent.OnTvShowClick(tvId)) }
    }

    private fun onCarousalClick(id: String, mediaType: MediaType) {
        if (mediaType == MediaType.MOVIE) {
            onMovieClick(id)
        } else {
            onTvShowClick(id)
        }
    }

    private fun onMovieClick(movieId: String) {
        viewModelScope.launch { _listingScreenEvents.emit(ListingScreenEvent.OnMovieClick(movieId)) }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            when (val result = movieRepository.getPopularMovies()) {
                is Result.Error -> {
                    _listingScreenState.update {
                        it.copy(
                            globalError = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            popularMovies = DataState.Success(result.data)
                        )
                    }
                }
            }
        }

    }

    private fun getTrendingMoviesAndShows() {
        viewModelScope.launch {
            when (val result = movieRepository.getTrending()) {
                is Result.Error -> {
                    _listingScreenState.update {
                        it.copy(
                            globalError = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            trendingMovies = DataState.Success(result.data)
                        )
                    }
                }
            }
        }
    }

    private fun getPopularTvShows() {
        viewModelScope.launch {
            when (val result = movieRepository.getPopularTvShows()) {
                is Result.Error -> {
                    _listingScreenState.update {
                        it.copy(
                            globalError = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            popularTvShows = DataState.Success(result.data)
                        )
                    }
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            when (val result = movieRepository.getTopRatedMovies()) {
                is Result.Error -> {
                    _listingScreenState.update {
                        it.copy(
                            globalError = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            topRatedMovies = DataState.Success(result.data)
                        )
                    }
                }
            }
        }
    }

}