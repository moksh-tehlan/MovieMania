package presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import moviemania.composeapp.generated.resources.Res
import moviemania.composeapp.generated.resources.bookmark_add_icon
import moviemania.composeapp.generated.resources.bookmark_check_icon
import moviemania.composeapp.generated.resources.bookmark_icon
import moviemania.composeapp.generated.resources.movie_icon
import moviemania.composeapp.generated.resources.rating_icon
import moviemania.composeapp.generated.resources.search_icon
import moviemania.composeapp.generated.resources.thumbsup_icon
import moviemania.composeapp.generated.resources.time_icon
import org.jetbrains.compose.resources.painterResource

val movieIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.movie_icon)

val searchIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.search_icon)

val bookmarkIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.bookmark_icon)

val ratingIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.rating_icon)

val thumbsUpIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.thumbsup_icon)

val timerIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.time_icon)

val bookmarkAddIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.bookmark_add_icon)

val bookmarkAddedIcon: Painter
    @Composable
    get() = painterResource(Res.drawable.bookmark_check_icon )