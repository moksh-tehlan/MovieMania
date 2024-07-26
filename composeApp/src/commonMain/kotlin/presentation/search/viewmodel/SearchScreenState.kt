package presentation.search.viewmodel

import domain.model.Media

data class SearchScreenState(
    val isLoading: Boolean = false,
    val searchBox:String = "",
    val error: String? = null,
    val searchResult: List<Media> = emptyList()
)