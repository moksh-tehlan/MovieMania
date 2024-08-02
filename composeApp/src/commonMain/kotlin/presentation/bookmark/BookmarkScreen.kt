package presentation.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import utils.DataState
import utils.DataStateView

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = koinViewModel(),
    onMovieClick: (String) -> Unit,
    onTvShowClick: (String) -> Unit,
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
    when {
        state.isEmpty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Bookmarked Items",
                    style = MaterialTheme.typography.h6.copy(
                        Color.White.copy(.75f)
                    )
                )
            }
        }

        else -> Column(
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
                        isBookmarked = movie.isBookmarked,
                        onBookmarkClick = { onAction(BookmarkActions.RemoveBookmark(movie.id)) },
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
                        isBookmarked = tvShow.isBookmarked,
                        onBookmarkClick = { onAction(BookmarkActions.RemoveBookmark(tvShow.id)) },
                        onClick = { onAction(BookmarkActions.OnTvShowClick(tvShow.id.toString())) }
                    )
                }
            }
        }
    }
}
