package utils

import androidx.compose.runtime.Composable

@Composable
fun <T> DataStateView(
    state: DataState<T>,
    loadingContent: @Composable () -> Unit = {},
    successContent: @Composable (data: T) -> Unit,
) {
    when (state) {
        is DataState.Loading -> loadingContent()
        is DataState.Success -> successContent(state.data)
    }
}