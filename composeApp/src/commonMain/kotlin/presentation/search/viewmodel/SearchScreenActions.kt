package presentation.search.viewmodel

sealed class SearchScreenActions{
    data class SearchQuery(val query: String) : SearchScreenActions()
}