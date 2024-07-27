package presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MediaType
import domain.repository.MovieDetailsRepository
import domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.NavScreens.MovieDetailScreen.MOVIE_ID
import navigation.NavScreens.MovieDetailScreen.TV_ID
import utils.DataState

class DetailScreenViewModel(
    private val movieDetailsRepository: MovieDetailsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _dataScreenState = MutableStateFlow(DetailScreenState())
    val dataScreenState = _dataScreenState.asStateFlow()

    init {
        val movieId = savedStateHandle.get<String>(MOVIE_ID)
        val tvId = savedStateHandle.get<String>(TV_ID)

        val id = movieId ?: tvId
        val mediaType =
            if (movieId != null) MediaType.MOVIE else if (tvId != null) MediaType.TV else null

        if (mediaType == null || id.isNullOrBlank()) {
            _dataScreenState.update {
                it.copy(
                    error = "Invalid Id :)"
                )
            }
        } else {
            _dataScreenState.update {
                it.copy(
                    mediaType = mediaType
                )
            }
            getMediaDetails(id, mediaType)
            getCast(id, mediaType)
            getRecommendedMedia(id, mediaType)
            getWatchProvider(id, mediaType)
        }
    }

    private fun getMediaDetails(id: String, mediaType: MediaType) {
        viewModelScope.launch {
            val result = when (mediaType) {
                MediaType.MOVIE -> movieDetailsRepository.getMovieDetails(id)
                MediaType.TV -> movieDetailsRepository.getTvShowDetails(id)
            }
            when (result) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            mediaDetails = DataState.Success(result.data)
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

    private fun getCast(id: String, mediaType: MediaType) {
        viewModelScope.launch {
            val result = when (mediaType) {
                MediaType.MOVIE -> movieDetailsRepository.getMovieCast(id)
                MediaType.TV -> movieDetailsRepository.getTvCast(id)
            }
            when (result) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            castState = DataState.Success(result.data)
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

    private fun getRecommendedMedia(id: String, mediaType: MediaType) {
        viewModelScope.launch {
            val result = when (mediaType) {
                MediaType.MOVIE -> movieDetailsRepository.getRecommendedMovies(id)
                MediaType.TV -> movieDetailsRepository.getTvRecommendedMovies(id)
            }
            when (result) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            recommendedState = DataState.Success(result.data)
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

    private fun getWatchProvider(id: String, mediaType: MediaType) {
        viewModelScope.launch {
            val result = when (mediaType) {
                MediaType.MOVIE -> movieDetailsRepository.getMovieWatchProvider(id)
                MediaType.TV -> movieDetailsRepository.getTvWatchProvider(id)
            }
            when (result) {
                is Result.Success -> {
                    _dataScreenState.update {
                        it.copy(
                            watchProviderState = DataState.Success(result.data)
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