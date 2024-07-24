package presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import navigation.HomeNavScreens
import navigation.HomeScreenGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.common.bookmarkIcon
import presentation.common.movieIcon
import presentation.common.searchIcon
import utils.MovieColor

@Composable
fun HomeScreen(
    navController: NavController
) {
    val homeNavController = rememberNavController()
    Scaffold(
        backgroundColor = MovieColor.Charcoal,
        contentColor = Color.White,
        bottomBar = {
            HomeScreenBottomBar(
                modifier = Modifier.fillMaxWidth().windowInsetsPadding(
                    insets = WindowInsets.navigationBars
                ),
                navController = homeNavController
            )
        }
    ) { innerPadding ->
        HomeScreenGraph(
            modifier = Modifier.padding(innerPadding),
            homeNavController = homeNavController,
            rootNavController = navController,
        )
    }
}

@Composable
private fun HomeScreenBottomBar(modifier: Modifier = Modifier, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute = navBackStackEntry?.destination?.route
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        HomeBottomNavigationItem(
            label = "Home",
            isActive = currentDestinationRoute == HomeNavScreens.MovieListingScreen.route,
            imageRes = movieIcon,
            onClick = {
                navController.navigate(HomeNavScreens.MovieListingScreen.route) {
                    popUpTo(navController.graph.findStartDestination().route ?: "") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
        HomeBottomNavigationItem(
            label = "Search",
            isActive = currentDestinationRoute == HomeNavScreens.SearchScreen.route,
            imageRes = searchIcon,
            onClick = {
                navController.navigate(HomeNavScreens.SearchScreen.route) {
                    popUpTo(navController.graph.findStartDestination().route ?: "") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
        HomeBottomNavigationItem(
            label = "Bookmarks",
            isActive = currentDestinationRoute == HomeNavScreens.BookmarksScreen.route,
            imageRes = bookmarkIcon,
            onClick = {
                navController.navigate(HomeNavScreens.BookmarksScreen.route) {
                    popUpTo(navController.graph.findStartDestination().route ?: "") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
    }
}

@Composable
fun HomeBottomNavigationItem(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    imageRes: Painter,
) {
    Column(
        modifier = Modifier.alpha(if (!isActive) 0.5f else 1f)
            .clickable(
                enabled = !isActive,
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = imageRes,
            modifier = Modifier.size(25.dp),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "tab_icon"
        )
        Spacer(Modifier.height(5.dp))
        Text(label, color = Color.White, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}