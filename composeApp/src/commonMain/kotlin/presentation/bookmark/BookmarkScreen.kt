package presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import presentation.bookmark.viewmodel.BookmarkActions
import presentation.bookmark.viewmodel.BookmarkEvent
import presentation.bookmark.viewmodel.BookmarkState
import presentation.bookmark.viewmodel.BookmarkViewModel
import presentation.common.HorizontalItemList
import presentation.common.HorizontalItemListShimmerEffect
import presentation.common.MediaItem
import presentation.common.MediaItemShimmerEffect
import presentation.common.ObserveAsEvents
import presentation.listing.viewmodel.ListingScreenEvent
import utils.DataStateView

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = koinViewModel(),
    onMovieClick:(String) -> Unit,
    onTvShowClick:(String) -> Unit
) {
    ObserveAsEvents(viewModel.bookmarkEvent) {
        when (it) {
            is BookmarkEvent.OnMovieClick -> {
                onMovieClick(it.movieId)
            }

            is BookmarkEvent.OnTvShowClick -> {
                onTvShowClick(it.tvId)
            }
        }
    }
    BookmarkScreenView(
        state = viewModel.bookmarkState.collectAsState().value,
        onAction = viewModel::onAction
    )
}

@Composable
private fun BookmarkScreenView(
    state: BookmarkState,
    onAction: (BookmarkActions) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(insets = WindowInsets.systemBars)
    ) {
        DataStateView(
            state = state.movies,
            loadingContent = {
                HorizontalItemListShimmerEffect(
                    item = { MediaItemShimmerEffect() }
                )
            }
        ) { movies ->
            HorizontalItemList(
                title = "Bookmarked Movies",
                itemList = movies
            ) { movie ->
                MediaItem(
                    posterPath = movie.posterPath,
                    title = movie.title,
                    subtitle = movie.releaseDate,
                    onClick = { onAction(BookmarkActions.OnMovieClick(movie.id.toString())) }
                )
            }
        }

        DataStateView(
            state = state.tvShows,
            loadingContent = {
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalItemListShimmerEffect(
                    item = { MediaItemShimmerEffect() }
                )
            }
        ) { tvShows ->
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalItemList(
                title = "Bookmarked Tv Shows",
                itemList = tvShows
            ) { tvShow ->
                MediaItem(
                    posterPath = tvShow.posterPath,
                    title = tvShow.title,
                    subtitle = tvShow.releaseDate,
                    onClick = { onAction(BookmarkActions.OnTvShowClick(tvShow.id.toString())) }
                )
            }
        }
    }
}
