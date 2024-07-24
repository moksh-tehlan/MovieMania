package domain.repository

import domain.model.AggregatedCast
import domain.model.Cast
import domain.model.MovieDetails
import domain.model.Movies
import domain.model.TvShowDetail
import domain.model.TvShows
import domain.model.WatchProvider
import domain.utils.DataError
import domain.utils.Result


interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: String): Result<MovieDetails, DataError.Network>
    suspend fun getMovieCast(movieId: String): Result<List<Cast>, DataError.Network>
    suspend fun getMovieWatchProvider(movieId: String): Result<List<WatchProvider>, DataError.Network>
    suspend fun getTvWatchProvider(tvId: String): Result<List<WatchProvider>, DataError.Network>
    suspend fun getRecommendedMovies(movieId: String): Result<List<Movies>, DataError.Network>
    suspend fun getTvRecommendedMovies(tvId: String): Result<List<TvShows>, DataError.Network>
    suspend fun getTvShowDetails(tvId: String): Result<TvShowDetail, DataError.Network>
    suspend fun getTvCast(tvId: String): Result<List<AggregatedCast>, DataError.Network>
}
