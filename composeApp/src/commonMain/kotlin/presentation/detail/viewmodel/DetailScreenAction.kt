package presentation.detail.viewmodel

sealed interface DetailScreenAction {
    data class ChangeSeason(val seasonNumber: Int) : DetailScreenAction
}