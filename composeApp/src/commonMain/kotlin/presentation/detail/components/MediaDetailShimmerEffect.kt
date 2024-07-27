package presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.MediaType
import presentation.common.BackDropShimmerEffect
import presentation.common.HorizontalItemListShimmerEffect
import presentation.common.shimmerEffect

@Composable
fun MediaDetailShimmerEffect(
    mediaType: MediaType,
) {
    BackDropShimmerEffect()
    Spacer(Modifier.height(25.dp))
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        StatItemShimmerEffect()
        StatItemShimmerEffect()

        if (mediaType == MediaType.MOVIE) {
            StatItemShimmerEffect()
        }
    }
    Spacer(Modifier.height(25.dp))
    Box(Modifier.padding(start = 15.dp).width(100.dp).height(15.dp).shimmerEffect())
    Spacer(Modifier.height(10.dp))
    LazyRow(
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(4) {
            GenreTitleShimmerEffect()
        }
    }
    if (mediaType == MediaType.TV) {
        Spacer(Modifier.height(25.dp))
        HorizontalItemListShimmerEffect {
            WatchProviderShimmerEffect()
        }
    }
}