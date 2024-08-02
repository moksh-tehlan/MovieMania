package presentation.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun BookmarkIcon(
    modifier: Modifier = Modifier,
    isBookmarked: Boolean = false,
    onBookmarkClick: () -> Unit,
) {
    val bookmarkColor = animateColorAsState(
        targetValue = if (isBookmarked) {
            Color(0xFFFFEB3B)
        } else {
            Color(0xFF9E9E9E).copy(alpha = 0.5f)
        },
        label = "Bookmark Color",
        animationSpec = tween(300)
    )
    Box(
        modifier = modifier
            .width(40.dp)
            .aspectRatio(1f / 1.5f)
            .clip(BookmarkIconShape())
            .background(bookmarkColor.value)
            .clickable{
                      onBookmarkClick()
            },
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.padding(bottom = 15.dp).size(25.dp),
            painter = if (isBookmarked) bookmarkAddedIcon else bookmarkAddIcon,
            tint = if (isBookmarked) Color.Black else Color.White,
            contentDescription = "Bookmark Icon"
        )
    }
}

private class BookmarkIconShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path().apply {
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(size.width / 2f, size.height / 1.25f)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }

}