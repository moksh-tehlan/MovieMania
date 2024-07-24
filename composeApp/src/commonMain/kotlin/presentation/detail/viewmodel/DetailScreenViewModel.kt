package presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MoviesAndShows
import domain.repository.MovieDetailsRepository
import domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.NavScreens.MovieDetailScreen.MOVIE_ID
import navigation.NavScreens.MovieDetailScreen.TV_ID

class DetailScreenViewModel(
    private val movieDetailsRepository: MovieDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataScreenState = MutableStateFlow(DetailScreenState())
    val dataScreenState = _dataScreenState.asStateFlow()

    fun onAction(action: DetailScreenAction) {
        when (action) {
            is DetailScreenAction.ChangeSeason -> {
                _dataScreenState.update {
                    it.copy(selectedSeason = action.seasonNumber)
                }
            }
        }
    }
    init {
        val movieId = savedStateHandle.get<String>(MOVIE_ID)
        val tvId = savedStateHandle.get<String>(TV_ID)
        println("movieId: $movieId")
        println("tvId: $tvId")
        if (movieId != null) {
            _dataScreenState.update {
                it.copy(mediaType = MoviesAndShows.MediaType.MOVIE)
            }
            getMovieDetails(movieId)
            getCast(movieId)
            getRecommendedMovies(movieId)
            getWatchProvider(movieId)
        } else if(tvId != null){
            _dataScreenState.update {
                it.copy(mediaType = MoviesAndShows.MediaType.TV)
            }
            getTvShowDetails(tvId)
            getTvCast(tvId)
            getTvWatchProvider(tvId)
            getTvShowRecommendations(tvId)
        }
        else {
            _dataScreenState.update {
                it.copy(error = "Movie ID is null")
            }
        }
    }

    private fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getMovieDetails(movieId = movieId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            movieDetailState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getTvShowDetails(tvId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getTvShowDetails(tvId = tvId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            tvDetailState = DetailScreenState.DataState.Success(result.data.copy(
                                seasons = result.data.seasons.filter { it.seasonNumber.toInt() != 0 }
                            ))
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getCast(movieId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getMovieCast(movieId = movieId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            castState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getRecommendedMovies(movieId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getRecommendedMovies(movieId = movieId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            recommendedMoviesState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getWatchProvider(movieId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getMovieWatchProvider(movieId = movieId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            watchProviderState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getTvWatchProvider(tvId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getTvWatchProvider(tvId = tvId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            watchProviderState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getTvShowRecommendations(tvId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getTvRecommendedMovies(tvId = tvId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            recommendedTvShowState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }

    private fun getTvCast(tvId: String) {
        viewModelScope.launch {
            when (val result = movieDetailsRepository.getTvCast(tvId = tvId)) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            tvCastState = DetailScreenState.DataState.Success(result.data)
                        )
                    }
                }

                is Result.Error -> {
                    _dataScreenState.update {
                        it.copy(
                            error = result.error.name
                        )
                    }
                }
            }
        }
    }
}