package navigation

sealed class NavScreens(
    val route: String,
) {
    data object HomeScreen : NavScreens(route = "home_screen")
    data object MovieDetailScreen : NavScreens(route = "movie_detail_screen"){
        const val MOVIE_ID:String = "movieId"
        const val TV_ID:String = "tvId"
    }
    fun createRoute(queryParameters: Map<String, String>): String {
        if (queryParameters.isEmpty()) return route
        val queryParamsString = queryParameters.map { "${it.key}=${it.value}" }
            .joinToString("&")
        println("createdRoute: $route?$queryParamsString")
        return "$route?$queryParamsString"
    }
}

sealed class HomeNavScreens(
    val route: String,
) {
    data object MovieListingScreen : HomeNavScreens(route = "movie_listing_screen")
    data object SearchScreen : HomeNavScreens(route = "search_screen")
    data object BookmarksScreen : HomeNavScreens(route = "bookmarks_screen")
}