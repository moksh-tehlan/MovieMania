package presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.dao.MediaDao
import data.mappers.toMediaEntity
import domain.model.Media
import domain.model.MediaDetails
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
    private val mediaDao: MediaDao,
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

    fun onAction(action: DetailScreenActions) {
        when (action) {
            is DetailScreenActions.OnBookmarkMediaDetails -> onBookmarkMediaDetails(action.mediaDetails)
            is DetailScreenActions.OnBookmarkMedia -> onBookmarkMedia(action.media)
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
                    val mediaDetails = if (result.data.seasons.isNotEmpty()) {
                        result.data.copy(seasons = result.data.seasons.drop(1))
                    } else {
                        result.data
                    }
                    _dataScreenState.update {
                        it.copy(
                            mediaDetails = DataState.Success(
                                mediaDetails.copy(
                                    isBookmarked = mediaDao.mediaExists(mediaDetails.id)
                                )
                            )
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
                            recommendedState = DataState.Success(result.data.map { media ->
                                media.copy(
                                    isBookmarked = mediaDao.mediaExists(media.id)
                                )
                            })
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

    private fun onBookmarkMediaDetails(mediaDetails: MediaDetails) {
        if (mediaDetails.isBookmarked) {
            removeBookmark(mediaDetails.id)
            return
        }
        val mediaItem = Media(
            id = mediaDetails.id,
            title = mediaDetails.title,
            posterPath = mediaDetails.posterPath,
            mediaType = mediaDetails.mediaType,
            overview = mediaDetails.overview,
            isBookmarked = mediaDetails.isBookmarked,
            releaseDate = mediaDetails.releaseDate,
            backdropPath = mediaDetails.backdropPath,
        )
        viewModelScope.launch {
            mediaDao.insertMedia(mediaItem.toMediaEntity().copy(isBookmarked = true))
        }
        _dataScreenState.update {
            val movieData = (it.mediaDetails as DataState.Success<MediaDetails>).data
            it.copy(
                mediaDetails = DataState.Success(movieData.copy(isBookmarked = true))
            )
        }
    }

    private fun onBookmarkMedia(media: Media) {
        if (media.isBookmarked) {
            removeBookmark(media.id,true)
            return
        }
        viewModelScope.launch {
            mediaDao.insertMedia(media.toMediaEntity().copy(isBookmarked = true))
        }
        _dataScreenState.update {
            val movieData = (it.recommendedState as DataState.Success<List<Media>>).data
            it.copy(
                recommendedState = DataState.Success(movieData.map { recommendedMedia ->
                    if (recommendedMedia.id == media.id) {
                        recommendedMedia.copy(
                            isBookmarked = true
                        )
                    } else recommendedMedia
                })
            )
        }
    }

    private fun removeBookmark(id: Long,isRecommendedMedia:Boolean = false) {
        viewModelScope.launch {
            mediaDao.deleteMedia(id)
        }
        if (isRecommendedMedia) {
            _dataScreenState.update {
                val mediaData = (it.recommendedState as DataState.Success<List<Media>>).data
                it.copy(
                    recommendedState = DataState.Success(mediaData.map { recommendedMedia ->
                        if (recommendedMedia.id == id) {
                            recommendedMedia.copy(
                                isBookmarked = false
                            )
                        } else recommendedMedia
                    })
                )
            }
        }else{
            _dataScreenState.update {
                val movieData = (it.mediaDetails as DataState.Success<MediaDetails>).data
                it.copy(
                    mediaDetails = DataState.Success(movieData.copy(isBookmarked = false))
                )
            }
        }
    }
}