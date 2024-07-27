package presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Media
import domain.model.MediaType
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.common.MovieNetworkImage
import presentation.common.ObserveAsEvents
import presentation.common.searchIcon
import presentation.common.shimmerEffect
import presentation.search.viewmodel.SearchScreenActions
import presentation.search.viewmodel.SearchScreenEvents
import presentation.search.viewmodel.SearchScreenState
import presentation.search.viewmodel.SearchScreenViewModel
import utils.MovieColor

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = koinViewModel(),
    onMovieClick: (String) -> Unit,
    onTvShowClick: (String) -> Unit,
) {
    ObserveAsEvents(viewModel.searchScreenEvents) {
        when (it) {
            is SearchScreenEvents.NavigateToMovieDetail -> onMovieClick(it.movieId)
            is SearchScreenEvents.NavigateToTvDetail -> onTvShowClick(it.tvId)
        }
    }

    SearchScreenView(
        state = viewModel.searchScreenState.collectAsState().value,
        onAction = viewModel::onAction
    )
}

@Composable
private fun SearchScreenView(
    state: SearchScreenState,
    onAction: (SearchScreenActions) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = 15.dp),
    ) {
        SearchBar(
            searchText = state.searchBox,
            onTextChange = { onAction(SearchScreenActions.SearchQuery(it)) }
        )
        when {
            state.isLoading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(20) {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight()
                        ) {
                            Box(
                                modifier = Modifier.height(100.dp).aspectRatio(1 / 1.5f).shimmerEffect()
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Box(Modifier.height(10.dp).width(100.dp).shimmerEffect())
                                Spacer(Modifier.height(5.dp))
                                Box(Modifier.height(10.dp).width(100.dp).shimmerEffect())
                            }
                        }

                    }
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                }
            }

            state.searchBox.isBlank() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Search for Movies or Tv Shows",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                }
            }

            state.searchResult.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Results Found",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.searchResult) { searchResult ->
                        ListingItem(searchResult = searchResult, onClick = { id, mediaType ->
                            onAction(SearchScreenActions.OnCardClick(id, mediaType))
                        })
                    }
                }
            }
        }
    }
}


@Composable
private fun ListingItem(
    modifier: Modifier = Modifier,
    searchResult: Media,
    onClick: (String, MediaType) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().wrapContentHeight()
            .clickable(onClick = { onClick(searchResult.id.toString(), searchResult.mediaType) })
    ) {
        MovieNetworkImage(
            modifier = Modifier.height(100.dp).aspectRatio(1 / 1.5f),
            url = searchResult.posterPath ?: ""
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = searchResult.title,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                )
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = searchResult.releaseDate,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.White.copy(.2f)
                )
            )
        }
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onTextChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 14.5.dp)
            .fillMaxWidth()
            .height(45.dp)
            .background(color = MovieColor.CharcoalLight)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(25.dp),
            painter = searchIcon,
            contentDescription = "Search Button",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(9.14.dp))
        BasicTextField(
            searchText,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                onTextChange(it)
            },
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.White
            )
        ) {
            it.invoke()
            if (searchText.isEmpty()) {
                Text(
                    text = "Search Movie or Tv Shows",
                    color = Color.White.copy(0.2f),
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }
        }
    }

}
