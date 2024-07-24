package presentation.detail.viewmodel

import data.model.TvShowDetailsDto
import domain.model.AggregatedCast
import domain.model.Cast
import domain.model.MovieDetails
import domain.model.Movies
import domain.model.MoviesAndShows
import domain.model.TvShowDetail
import domain.model.TvShows
import domain.model.WatchProvider

data class DetailScreenState(
    val movieDetailState: DataState<MovieDetails> = DataState.Loading,
    val tvDetailState: DataState<TvShowDetail> = DataState.Loading,
    val castState: DataState<List<Cast>> = DataState.Loading,
    val tvCastState: DataState<List<AggregatedCast>> = DataState.Loading,
    val recommendedMoviesState: DataState<List<Movies>> = DataState.Loading,
    val recommendedTvShowState: DataState<List<TvShows>> = DataState.Loading,
    val watchProviderState : DataState<List<WatchProvider>> = DataState.Loading,
    val mediaType: MoviesAndShows.MediaType?=null,
    val selectedSeason:Int = 1,
    val error: String? = null,
) {
    sealed interface DataState<out T> {
        data object Loading : DataState<Nothing>
        data class Success<T>(val data: T) : DataState<T>
    }
}
