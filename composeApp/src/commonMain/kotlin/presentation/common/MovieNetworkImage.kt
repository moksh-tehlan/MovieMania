package presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import data.IMAGE_URL
import utils.MovieColor

@Composable
fun MovieNetworkImage(modifier: Modifier = Modifier, url: String?) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = IMAGE_URL + url,
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize().shimmerEffect(),
            )
        },
        error = {
            Box(
                modifier = Modifier.fillMaxSize().background(MovieColor.veryLightGray.copy(.12f)),
            )
        },
        contentDescription = "Movie Image",
    )
}