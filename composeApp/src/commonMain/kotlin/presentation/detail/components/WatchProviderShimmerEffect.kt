package presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.common.shimmerEffect

@Composable
fun WatchProviderShimmerEffect() {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier.width(200.dp).aspectRatio(1.5f / 1f).shimmerEffect()
        )
        Spacer(Modifier.height(5.dp))
        Box(
            modifier = Modifier.width(100.dp).height(10.dp).shimmerEffect()
        )
        Spacer(Modifier.height(5.dp))
        Box(
            modifier = Modifier.width(100.dp).height(10.dp).shimmerEffect()
        )
    }
}