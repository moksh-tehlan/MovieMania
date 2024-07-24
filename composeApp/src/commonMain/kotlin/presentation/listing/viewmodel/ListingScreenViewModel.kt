package presentation.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MoviesAndShows
import domain.repository.MovieRepository
import domain.utils.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListingScreenViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _listingScreenState = MutableStateFlow(ListingScreenState())
    val listingScreenState = _listingScreenState.asStateFlow()

    private val _listingScreenEvents = MutableSharedFlow<ListingScreenEvent>()
    val listingScreenEvents = _listingScreenEvents.asSharedFlow()

    init {
        _listingScreenState.update { it.copy(isLoading = true) }

        getTrendingMoviesAndShows()
        getPopularMovies()
        getPopularTvShows()
        getTopRatedMovies()

        _listingScreenState.update { it.copy(isLoading = false) }
    }

    fun onAction(listingScreenAction: ListingScreenActions) {
        when (listingScreenAction) {
            is ListingScreenActions.OnMovieClick -> onMovieClick(listingScreenAction.movieId)
            is ListingScreenActions.OnCarousalClick -> onCarousalClick(listingScreenAction.pageNo)
            is ListingScreenActions.OnTvShowClick -> onTvShowClick(listingScreenAction.tvId)
        }
    }

    private fun onTvShowClick(tvId: String) {
        viewModelScope.launch { _listingScreenEvents.emit(ListingScreenEvent.OnTvShowClick(tvId)) }
    }

    private fun onCarousalClick(pageNo: Int) {
        val trending = _listingScreenState.value.trendingMovies[pageNo]
        if (trending.mediaType == MoviesAndShows.MediaType.MOVIE) {
            onMovieClick(trending.id.toString())
        } else {
            onTvShowClick(trending.id.toString())
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
                            error = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            popularMovies = result.data
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
                            error = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            trendingMovies = result.data
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
                            error = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            popularTvShows = result.data
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
                            error = result.error.name
                        )
                    }
                }

                is Result.Success -> {
                    _listingScreenState.update {
                        it.copy(
                            topRatedMovies = result.data
                        )
                    }
                }
            }
        }
    }

}