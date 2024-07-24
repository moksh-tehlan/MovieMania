package presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.MoviesAndShows
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersHolder
import presentation.common.HorizontalItemList
import presentation.common.MediaItem
import presentation.common.MovieNetworkImage
import presentation.common.ratingIcon
import presentation.common.thumbsUpIcon
import presentation.common.timerIcon
import presentation.detail.viewmodel.DetailScreenAction
import presentation.detail.viewmodel.DetailScreenState
import presentation.detail.viewmodel.DetailScreenViewModel
import utils.MovieColor

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DetailScreen(
    parametersHolder: ParametersHolder,
    viewModel: DetailScreenViewModel = koinViewModel<DetailScreenViewModel>(parameters = { parametersHolder })
) {
    val state = viewModel.dataScreenState.collectAsState().value
    when (state.mediaType) {
        MoviesAndShows.MediaType.MOVIE -> {
            MovieDetailScreenView(
                state = state
            )
        }

        MoviesAndShows.MediaType.TV -> {
            println("TV Show Detail ScreenT")
            TvDetailScreenView(
                state = state,
                onAction = viewModel::onAction
            )
        }

        else -> {
            Text("Error")
        }
    }
}

@Composable
private fun MovieDetailScreenView(
    state: DetailScreenState,
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
            when (val movieDetailState = state.movieDetailState) {
                is DetailScreenState.DataState.Loading -> {}
                is DetailScreenState.DataState.Success -> {
                    val movieDetail = movieDetailState.data
                    Box(modifier = Modifier.fillMaxWidth().height(335.dp)) {
                        MovieNetworkImage(
                            modifier = Modifier.fillMaxWidth().height(250.dp),
                            url = movieDetail.backdropPath,
                        )
                        Row(
                            modifier = Modifier.wrapContentSize().align(Alignment.BottomStart),
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            MovieNetworkImage(
                                modifier = Modifier.padding(start = 15.dp).width(100.dp)
                                    .height(150.dp),
                                url = movieDetail.posterPath,
                            )
                            Spacer(Modifier.width(15.dp))
                            Column(modifier = Modifier.wrapContentSize().padding(end = 15.dp)) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = movieDetail.title,
                                    style = MaterialTheme.typography.body1,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    text = movieDetail.overview,
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
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatItem(
                            value = "${
                                kotlin.math.round(
                                    (movieDetail.voteAverage) * 10.0
                                ) / 10.0
                            }/10",
                            icon = ratingIcon,
                            contentDescription = "Rating",
                            iconTint = Color.Yellow
                        )

                        StatItem(
                            value = movieDetail.voteCount,
                            icon = thumbsUpIcon,
                            contentDescription = "Votes"
                        )

                        StatItem(
                            value = movieDetail.formattedRunTime,
                            icon = timerIcon,
                            contentDescription = "Time"
                        )
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
                        items(movieDetail.genres) { genre ->
                            GenresTile(genre.name)
                        }
                    }
                }
            }
            when (val watchProviderState = state.watchProviderState) {
                is DetailScreenState.DataState.Loading -> {}
                is DetailScreenState.DataState.Success -> {
                    val watchProviders = watchProviderState.data
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
            }
            Spacer(Modifier.height(25.dp))
            // cast data
            when (val castState = state.castState) {
                is DetailScreenState.DataState.Loading -> {}

                is DetailScreenState.DataState.Success -> {
                    val castList = castState.data
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
            }

            Spacer(Modifier.height(25.dp))

            // cast data
            when (val movieState = state.recommendedMoviesState) {
                is DetailScreenState.DataState.Loading -> {}

                is DetailScreenState.DataState.Success -> {
                    val movieList = movieState.data
                    HorizontalItemList(
                        title = "Recommended Movies",
                        itemList = movieList
                    ) { movie ->
                        MediaItem(
                            posterPath = movie.posterPath,
                            title = movie.title,
                            subtitle = movie.releaseDate,
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun TvDetailScreenView(
    state: DetailScreenState,
    onAction: (DetailScreenAction) -> Unit,
) {
    Scaffold(
        backgroundColor = MovieColor.Charcoal
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
                when (val tvShowDetailDataState = state.tvDetailState) {
                    is DetailScreenState.DataState.Loading -> {}
                    is DetailScreenState.DataState.Success -> {
                        val tvShowDetail = tvShowDetailDataState.data
                        Box(modifier = Modifier.fillMaxWidth().height(335.dp)) {
                            MovieNetworkImage(
                                modifier = Modifier.fillMaxWidth().height(250.dp),
                                url = tvShowDetail.backdropPath,
                            )
                            Row(
                                modifier = Modifier.wrapContentSize().align(Alignment.BottomStart),
                                verticalAlignment = Alignment.Bottom,
                            ) {
                                MovieNetworkImage(
                                    modifier = Modifier.padding(start = 15.dp).width(100.dp)
                                        .height(150.dp),
                                    url = tvShowDetail.posterPath,
                                )
                                Spacer(Modifier.width(15.dp))
                                Column(modifier = Modifier.wrapContentSize().padding(end = 15.dp)) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = tvShowDetail.name,
                                        style = MaterialTheme.typography.body1,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.White
                                    )
                                    Spacer(Modifier.height(5.dp))
                                    Text(
                                        text = tvShowDetail.overview,
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
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatItem(
                                value = "${
                                    kotlin.math.round(
                                        (tvShowDetail.voteAverage) * 10.0
                                    ) / 10.0
                                }/10",
                                icon = ratingIcon,
                                contentDescription = "Rating",
                                iconTint = Color.Yellow
                            )

                            StatItem(
                                value = tvShowDetail.voteCount,
                                icon = thumbsUpIcon,
                                contentDescription = "Votes"
                            )

                            StatItem(
                                value = tvShowDetail.episodeRunTime,
                                icon = timerIcon,
                                contentDescription = "Time"
                            )
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
                            items(tvShowDetail.genres) { genre ->
                                GenresTile(genre.name)
                            }
                        }
                        Spacer(Modifier.height(25.dp))
                        HorizontalItemList(
                            title = "Seasons ${tvShowDetail.seasons.size}",
                            itemList = tvShowDetail.seasons
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
                when (val watchProviderState = state.watchProviderState) {
                    is DetailScreenState.DataState.Loading -> {}
                    is DetailScreenState.DataState.Success -> {
                        val watchProviders = watchProviderState.data
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
                }
                Spacer(Modifier.height(25.dp))
                // cast data
                when (val castState = state.tvCastState) {
                    is DetailScreenState.DataState.Loading -> {}

                    is DetailScreenState.DataState.Success -> {
                        val castList = castState.data
                        HorizontalItemList(
                            title = "Cast",
                            itemList = castList
                        ) { cast ->
                            MediaItem(
                                posterPath = cast.profilePath,
                                title = cast.name,
                                subtitle = cast.roles.first().character,
                                onClick = { }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(25.dp))

                // cast data
                when (val tvShowState = state.recommendedTvShowState) {
                    is DetailScreenState.DataState.Loading -> {}

                    is DetailScreenState.DataState.Success -> {
                        val tvShowsList = tvShowState.data
                        HorizontalItemList(
                            title = "Recommended Movies",
                            itemList = tvShowsList
                        ) { movie ->
                            MediaItem(
                                posterPath = movie.posterPath,
                                title = movie.name,
                                subtitle = movie.firstAirDate,
                                onClick = { }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(
    value: Any, // Can handle Double or Long
    icon: Painter,
    contentDescription: String,
    iconTint: Color = Color.White // Default icon tint
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            painter = icon,
            contentDescription = contentDescription,
            tint = iconTint
        )
        Spacer(Modifier.height(5.dp))
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
    }
}

@Composable
private fun GenresTile(
    genre: String,
) {
    Box(
        Modifier.wrapContentSize().border(width = 1.dp, color = Color.White)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(genre, color = Color.White, style = MaterialTheme.typography.caption)
    }
}

@Composable
fun SeasonDropdownMenu(
    seasonsCount: Long,
    selectedSeason: Int,
    onSeasonSelect: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val seasonList = List(seasonsCount.toInt()) { it + 1 }
    Box(
        modifier = Modifier.padding(start = 15.dp).background(MovieColor.CharcoalLight)
            .padding(horizontal = 15.dp, vertical = 5.dp)
    ) {
        Text(
            text = "Season $selectedSeason",
            color = Color.White,
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            modifier = Modifier.wrapContentSize().background(MovieColor.CharcoalLight).clip(
                RoundedCornerShape(10.dp)
            ),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            seasonList.forEach { season ->
                DropdownMenuItem(onClick = {
                    onSeasonSelect(season)
                    expanded = false
                }) {
                    Text("Season $season", color = Color.White)
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

