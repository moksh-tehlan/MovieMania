package presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> HorizontalItemList(
    title: String?=null,
    itemList: List<T>,
    itemContent: @Composable (item: T,count:Int) -> Unit
) {
    if (itemList.isEmpty())return
    Column {
        if (title != null){
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = title,
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(items = itemList) { count,item ->
                itemContent(item,count)
            }
        }
    }
}

@Composable
fun <T> HorizontalItemList(
    title: String?=null,
    itemList: List<T>,
    itemContent: @Composable (item: T) -> Unit
) {
    if (itemList.isEmpty())return
    Column {
        if (title != null){
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = title,
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = itemList) { item ->
                itemContent(item)
            }
        }
    }
}
