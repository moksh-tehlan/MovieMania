package presentation.detail.viewmodel

import domain.model.Cast
import domain.model.Media
import domain.model.MediaDetails
import domain.model.MediaType
import domain.model.WatchProvider
import utils.DataState

data class DetailScreenState(
    val mediaDetails: DataState<MediaDetails> = DataState.Loading,
    val recommendedState: DataState<List<Media>> = DataState.Loading,
    val castState: DataState<List<Cast>> = DataState.Loading,
    val watchProviderState: DataState<List<WatchProvider>> = DataState.Loading,
    val mediaType: MediaType?=null,
    val error: String? = null,
)
