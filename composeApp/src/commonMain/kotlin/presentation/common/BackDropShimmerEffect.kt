package presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackDropShimmerEffect() {
    Column {
        Box(Modifier.fillMaxWidth().height(250.dp).shimmerEffect())
        Row(modifier = Modifier.padding(horizontal = 15.dp)) {
            Box(Modifier.height(85.dp).width(100.dp).shimmerEffect())
            Spacer(Modifier.width(15.dp))
            Column {
                Spacer(Modifier.height(15.dp))
                Box(Modifier.fillMaxWidth().height(15.dp).shimmerEffect())
                Spacer(Modifier.height(10.dp))
                Box(Modifier.fillMaxWidth().height(10.dp).shimmerEffect())
                Spacer(Modifier.height(5.dp))
                Box(Modifier.fillMaxWidth().height(10.dp).shimmerEffect())
                Spacer(Modifier.height(5.dp))
                Box(Modifier.fillMaxWidth().height(10.dp).shimmerEffect())
            }
        }
    }
}