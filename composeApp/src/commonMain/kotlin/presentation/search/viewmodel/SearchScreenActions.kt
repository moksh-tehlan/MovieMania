package presentation.search.viewmodel

import domain.model.MediaType
import domain.model.MoviesAndShows

sealed class SearchScreenActions{
    data class SearchQuery(val query: String) : SearchScreenActions()
    data class OnCardClick(val id: String,val mediaType: MediaType) : SearchScreenActions()
}