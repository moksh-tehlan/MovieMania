package presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MediaType
import domain.model.MoviesAndShows
import domain.repository.MovieRepository
import domain.utils.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _searchScreenState = MutableStateFlow(SearchScreenState())
    val searchScreenState = _searchScreenState.asStateFlow()

    private val _searchScreenEvents = MutableSharedFlow<SearchScreenEvents>()
    val searchScreenEvents = _searchScreenEvents.asSharedFlow()

    private var searchJob: Job? = null

    fun onAction(action: SearchScreenActions) {
        when (action) {
            is SearchScreenActions.SearchQuery -> changeSearchText(action.query)
            is SearchScreenActions.OnCardClick -> navigateToDetail(action.id, action.mediaType)
        }
    }

    private fun navigateToDetail(id: String, mediaType: MediaType) {
        viewModelScope.launch {
            when (mediaType) {
                MediaType.MOVIE -> _searchScreenEvents.emit(
                    SearchScreenEvents.NavigateToMovieDetail(
                        id
                    )
                )

                MediaType.TV -> _searchScreenEvents.emit(
                    SearchScreenEvents.NavigateToTvDetail(
                        id
                    )
                )
            }
        }
    }

    private fun changeSearchText(query: String) {
        _searchScreenState.update {
            it.copy(
                searchBox = query
            )
        }
        searchJob?.cancel()
        if (query.isNotBlank())
            searchQuery(query)
        else
            _searchScreenState.update {
                it.copy(
                    searchResult = emptyList()
                )
            }
    }

    private fun searchQuery(query: String) {

        viewModelScope.launch {
            delay(1000)
            _searchScreenState.update {
                it.copy(
                    isLoading = _searchScreenState.value.searchResult.isEmpty()
                )
            }
            when (val result = movieRepository.searchQuery(query)) {
                is Result.Success -> {
                    _searchScreenState.update {
                        it.copy(
                            searchResult = result.data,
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {
                    _searchScreenState.update {
                        it.copy(
                            error = result.error.name,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}