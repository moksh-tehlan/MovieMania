package presentation.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.common.HorizontalItemList
import presentation.common.MediaItem
import presentation.common.ObserveAsEvents
import presentation.listing.components.HomeCarousel
import presentation.listing.viewmodel.ListingScreenActions
import presentation.listing.viewmodel.ListingScreenEvent
import presentation.listing.viewmodel.ListingScreenState
import presentation.listing.viewmodel.ListingScreenViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ListingScreen(
    viewModel: ListingScreenViewModel = koinViewModel(),
    onMovieClick: (String) -> Unit,
    onTvShowClick:(String) -> Unit
) {
    ObserveAsEvents(viewModel.listingScreenEvents) {
        when (it) {
            is ListingScreenEvent.OnMovieClick -> {
                println("movieIds: ${it.movieId}")
                onMovieClick(it.movieId)
            }

            is ListingScreenEvent.OnTvShowClick -> {
                println("tvIds: ${it.tvId}")
                onTvShowClick(it.tvId)}
        }
    }
    ListingScreenView(
        state = viewModel.listingScreenState.collectAsState().value,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ListingScreenView(
    state: ListingScreenState,
    onAction: (ListingScreenActions) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        HomeCarousel(
            trendingList = state.trendingMovies,
            onItemClick = {
                onAction(ListingScreenActions.OnCarousalClick(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalItemList(
            title = "Top Rated Movies",
            itemList = state.topRatedMovies
        ) { movie ->
            MediaItem(
                posterPath = movie.posterPath,
                title = movie.title,
                subtitle = movie.releaseDate,
                onClick = { onAction(ListingScreenActions.OnMovieClick(movie.id.toString())) }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalItemList(
            title = "Popular Movies",
            itemList = state.popularMovies
        ) { movie ->
            MediaItem(
                posterPath = movie.posterPath,
                title = movie.title,
                subtitle = movie.releaseDate,
                onClick = { onAction(ListingScreenActions.OnMovieClick(movie.id.toString())) }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalItemList(
            title = "Popular Tv Shows",
            itemList = state.popularTvShows
        ) { tvShow ->
            MediaItem(
                posterPath = tvShow.posterPath,
                title = tvShow.originalName,
                subtitle = tvShow.firstAirDate,
                onClick = { onAction(ListingScreenActions.OnTvShowClick(tvShow.id.toString())) }
            )
        }
    }
}
