package navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import navigation.NavScreens.MovieDetailScreen.MOVIE_ID
import navigation.NavScreens.MovieDetailScreen.TV_ID
import org.koin.core.parameter.parametersOf
import presentation.detail.DetailScreen
import presentation.home.HomeScreen
import presentation.listing.ListingScreen
import presentation.search.SearchScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = NavScreens.HomeScreen.route,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(500),
                initialOffsetX = { it }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(500),
                targetOffsetX = { it }
            )
        }
    ) {
        composable(route = NavScreens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = NavScreens.MovieDetailScreen.createRoute(
                mapOf(MOVIE_ID to "{movieId}", TV_ID to "{tvId}")
            ),
            arguments = listOf(
                navArgument(MOVIE_ID) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(TV_ID) {
                    type = NavType.StringType
                    nullable = true
                },
            )
        ) {
            Box {
                DetailScreen(
                    parametersHolder = parametersOf(
                        SavedStateHandle.createHandle(
                            null,
                            it.arguments
                        )
                    ),
                    onMovieClick = {
                        navController.navigate(
                            route = NavScreens.MovieDetailScreen,
                            queryParams = mapOf(
                                MOVIE_ID to it
                            )
                        )
                    },
                    onTvShowClick = {
                        navController.navigate(
                            route = NavScreens.MovieDetailScreen,
                            queryParams = mapOf(
                                TV_ID to it
                            )
                        )
                    }
                )
                Box(
                    Modifier.fillMaxWidth().height(250.dp).background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Transparent
                            ),
                            startY = 0f,
                            endY = 550f
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun HomeScreenGraph(
    modifier: Modifier = Modifier,
    homeNavController: NavHostController,
    rootNavController: NavController,
) {
    NavHost(
        modifier = modifier,
        navController = homeNavController,
        startDestination = HomeNavScreens.MovieListingScreen.route
    ) {
        composable(route = HomeNavScreens.MovieListingScreen.route) {
            Box(Modifier.fillMaxSize()) {
                ListingScreen(
                    onMovieClick = {
                        rootNavController.navigate(
                            route = NavScreens.MovieDetailScreen,
                            queryParams = mapOf(
                                MOVIE_ID to it
                            )
                        )
                    },
                    onTvShowClick = {
                        rootNavController.navigate(
                            route = NavScreens.MovieDetailScreen,
                            queryParams = mapOf(
                                TV_ID to it
                            )
                        )
                    }
                )
                Box(
                    Modifier.fillMaxWidth().height(250.dp).background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Transparent
                            ),
                            startY = 0f,
                            endY = 550f
                        )
                    )
                )
            }
        }
        composable(route = HomeNavScreens.SearchScreen.route) {
            SearchScreen(
                onMovieClick = {
                    rootNavController.navigate(
                        route = NavScreens.MovieDetailScreen,
                        queryParams = mapOf(
                            MOVIE_ID to it
                        )
                    )
                },
                onTvShowClick = {
                    rootNavController.navigate(
                        route = NavScreens.MovieDetailScreen,
                        queryParams = mapOf(
                            TV_ID to it
                        )
                    )
                }
            )
        }
        composable(route = HomeNavScreens.BookmarksScreen.route) {
            Box(Modifier.fillMaxSize()) {
                Text("Bookmarks Screen", style = MaterialTheme.typography.h2)
            }
        }
    }
}

fun NavHostController.navigate(route: NavScreens, queryParams: Map<String, String> = emptyMap()) {
    navigate(route.createRoute(queryParams)) {
        popUpTo(NavScreens.HomeScreen.route) {
        }
        launchSingleTop = true
    }
}

fun NavController.navigate(route: NavScreens, queryParams: Map<String, String> = emptyMap()) {
    navigate(route.createRoute(queryParams)) {
        popUpTo(NavScreens.HomeScreen.route) {
        }
        launchSingleTop = true
    }
}