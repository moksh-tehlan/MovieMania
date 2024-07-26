package presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import data.IMAGE_URL
import io.github.aakira.napier.Napier
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