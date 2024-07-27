package presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalItemListShimmerEffect(
    item: @Composable () -> Unit,
) {
    Column {
        Box(
            modifier = Modifier.padding(start = 15.dp).width(200.dp).height(20.dp).shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(5) {
                item()
            }
        }
    }
}