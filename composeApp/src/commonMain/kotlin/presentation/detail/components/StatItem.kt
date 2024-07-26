package presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun StatItem(
    value: Any, // Can handle Double or Long
    icon: Painter,
    contentDescription: String,
    iconTint: Color = Color.White, // Default icon tint
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