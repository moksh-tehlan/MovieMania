package presentation.search.viewmodel

import domain.model.MoviesAndShows
import domain.model.Search

data class SearchScreenState(
    val isLoading: Boolean = false,
    val searchBox:String = "",
    val error: String? = null,
    val searchResult: List<Search> = emptyList()
)