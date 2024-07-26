package presentation.search.viewmodel

import domain.model.MediaType

sealed class SearchScreenActions{
    data class SearchQuery(val query: String) : SearchScreenActions()
    data class OnCardClick(val id: String,val mediaType: MediaType) : SearchScreenActions()
}