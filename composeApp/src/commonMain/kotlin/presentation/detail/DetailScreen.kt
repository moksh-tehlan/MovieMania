package presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.MediaType
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersHolder
import presentation.common.HorizontalItemList
import presentation.common.MediaItem
import presentation.common.MovieNetworkImage
import presentation.common.ratingIcon
import presentation.common.thumbsUpIcon
import presentation.common.timerIcon
import presentation.detail.components.GenresTile
import presentation.detail.components.StatItem
import presentation.detail.viewmodel.DetailScreenState
import presentation.detail.viewmodel.DetailScreenViewModel
import utils.DataStateView
import utils.MovieColor

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DetailScreen(
    parametersHolder: ParametersHolder,
    onMovieClick: (String) -> Unit,
    onTvShowClick: (String) -> Unit,
    viewModel: DetailScreenViewModel = koinViewModel<DetailScreenViewModel>(parameters = { parametersHolder }),
) {
    val state = viewModel.dataScreenState.collectAsState().value
    DetailScreenView(
        state = state,
        onMediaClick = { id, mediaType ->
            if (mediaType == MediaType.MOVIE) {
                onMovieClick(id)
            } else {
                onTvShowClick(id)
            }
        }
    )
}

@Composable
private fun DetailScreenView(
    state: DetailScreenState,
    onMediaClick: (String, MediaType) -> Unit,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MovieColor.Charcoal
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            // movie detail data
            DataStateView(
                state = state.mediaDetails,
            ) { mediaDetail ->
                Box(modifier = Modifier.fillMaxWidth().height(335.dp)) {
                    MovieNetworkImage(
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        url = mediaDetail.backdropPath,
                    )
                    Row(
                        modifier = Modifier.wrapContentSize().align(Alignment.BottomStart),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        MovieNetworkImage(
                            modifier = Modifier.padding(start = 15.dp).width(100.dp)
                                .height(150.dp),
                            url = mediaDetail.posterPath,
                        )
                        Spacer(Modifier.width(15.dp))
                        Column(modifier = Modifier.wrapContentSize().padding(end = 15.dp)) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = mediaDetail.title,
                                style = MaterialTheme.typography.body1,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = mediaDetail.overview,
                                color = Color.White.copy(alpha = 0.7f),
                                style = MaterialTheme.typography.subtitle2,
                                maxLines = 3,
                                lineHeight = 16.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Spacer(Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    StatItem(
                        value = "${
                            kotlin.math.round(
                                (mediaDetail.voteAverage) * 10.0
                            ) / 10.0
                        }/10",
                        icon = ratingIcon,
                        contentDescription = "Rating",
                        iconTint = Color.Yellow
                    )

                    StatItem(
                        value = mediaDetail.voteCount,
                        icon = thumbsUpIcon,
                        contentDescription = "Votes"
                    )

                    if (mediaDetail.runtime != null) {
                        StatItem(
                            value = mediaDetail.runtime,
                            icon = timerIcon,
                            contentDescription = "Time"
                        )
                    }
                }
                Spacer(Modifier.height(25.dp))
                Text(
                    "Genres",
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Spacer(Modifier.height(10.dp))
                LazyRow(
                    contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(mediaDetail.genres) { genre ->
                        GenresTile(genre.name)
                    }
                }
                if (mediaDetail.seasons.isNotEmpty()) {
                    Spacer(Modifier.height(25.dp))
                    HorizontalItemList(
                        title = "Seasons ${mediaDetail.seasons.size}",
                        itemList = mediaDetail.seasons
                    ) { season ->
                        Column(
                            horizontalAlignment = Alignment.Start,
                        ) {
                            MovieNetworkImage(
                                modifier = Modifier.width(200.dp).aspectRatio(1.5f / 1f),
                                url = season.posterPath
                            )
                            Text(
                                text = season.name,
                                color = Color.White,
                            )
                            Text(
                                text = "Episodes ${season.episodeCount}",
                                color = Color.White,
                            )
                        }
                    }
                }
            }

            DataStateView(
                state = state.watchProviderState,
            ) { watchProviders ->
                Spacer(Modifier.height(25.dp))
                HorizontalItemList(
                    title = "Watch Providers",
                    itemList = watchProviders
                ) { provider ->
                    WatchProvider(
                        logo = provider.logoPath
                    )
                }
            }

            // cast data
            DataStateView(state = state.castState) { castList ->
                Spacer(Modifier.height(25.dp))
                HorizontalItemList(
                    title = "Cast",
                    itemList = castList
                ) { cast ->
                    MediaItem(
                        posterPath = cast.profilePath,
                        title = cast.name,
                        subtitle = cast.character,
                        onClick = { }
                    )
                }
            }

            // recommended
            DataStateView(
                state = state.recommendedState,
            ) { movieList ->
                Spacer(Modifier.height(25.dp))
                HorizontalItemList(
                    title = "Recommended",
                    itemList = movieList
                ) { movie ->
                    MediaItem(
                        posterPath = movie.posterPath,
                        title = movie.title,
                        subtitle = movie.releaseDate,
                        onClick = { onMediaClick(movie.id.toString(), movie.mediaType) }
                    )
                }
            }

        }
    }


}

@Composable
fun WatchProvider(
    logo: String,
) {
    MovieNetworkImage(
        modifier = Modifier.height(50.dp).aspectRatio(1f).clip(RoundedCornerShape(5.dp)),
        url = logo
    )
}

