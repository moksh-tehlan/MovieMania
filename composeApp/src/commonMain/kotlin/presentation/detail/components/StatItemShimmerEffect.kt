package presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.common.shimmerEffect

@Composable
fun StatItemShimmerEffect() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(25.dp).shimmerEffect()
        )
        Spacer(Modifier.height(5.dp))
        Box(Modifier.width(100.dp).height(15.dp).shimmerEffect())
    }
}