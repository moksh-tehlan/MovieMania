package presentation.listing.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Media
import domain.model.MediaType
import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import presentation.common.MovieNetworkImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCarousel(
    modifier: Modifier = Modifier,
    onItemClick: (Long,MediaType) -> Unit,
    trendingList: List<Media>
) {
    Napier.d("HomeCarousel: ${trendingList.size}")
    val thumbnailPagerState = rememberPagerState(initialPage = 0, pageCount = { trendingList.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(6000L)
            val nextPage = (thumbnailPagerState.currentPage + 1) % thumbnailPagerState.pageCount
            thumbnailPagerState.animateScrollToPage(page = nextPage, animationSpec = tween(800))
        }
    }

    HorizontalPager(
        modifier = Modifier.wrapContentSize(),
        state = thumbnailPagerState,
    ) { page ->
        Box(modifier = Modifier.fillMaxWidth().height(335.dp).clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { onItemClick(trendingList[page].id, trendingList[page].mediaType) }
        )) {
            MovieNetworkImage(
                modifier = Modifier.fillMaxWidth().height(250.dp),
                url = trendingList[page].backdropPath,
            )
            Row(
                modifier = Modifier.wrapContentSize().align(Alignment.BottomStart),
                verticalAlignment = Alignment.Bottom,
            ) {
                MovieNetworkImage(
                    modifier = Modifier.padding(start = 15.dp).width(100.dp).height(150.dp),
                    url = trendingList[page].posterPath,
                )
                Spacer(Modifier.width(15.dp))
                Column(modifier = Modifier.wrapContentSize().padding(end = 15.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = trendingList[page].title,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = trendingList[page].overview,
                        color = Color.White.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.subtitle2,
                        maxLines = 3,
                        lineHeight = 16.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

