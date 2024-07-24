package presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import utils.MovieColor


@Composable
fun MediaItem(
    posterPath: String?,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(10.dp))
            .background(color = MovieColor.CharcoalLight)
            .clickable(onClick = onClick)
    ) {
        MovieNetworkImage(
            modifier = Modifier
                .height(200.dp)
                .aspectRatio(1f / 1.5f),
            url = posterPath
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .width(133.dp)
                .height(40.dp)
                .padding(horizontal = 10.dp),
            text = title,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .width(133.dp)
                .padding(horizontal = 10.dp),
            text = subtitle,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White.copy(0.5f)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}