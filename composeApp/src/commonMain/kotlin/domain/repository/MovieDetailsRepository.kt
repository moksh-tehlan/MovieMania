package domain.repository

import domain.model.Cast
import domain.model.Media
import domain.model.MediaDetails
import domain.model.WatchProvider
import domain.utils.DataError
import domain.utils.Result


interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: String): Result<MediaDetails, DataError.Network>
    suspend fun getMovieCast(movieId: String): Result<List<Cast>, DataError.Network>
    suspend fun getMovieWatchProvider(movieId: String): Result<List<WatchProvider>, DataError.Network>
    suspend fun getTvWatchProvider(tvId: String): Result<List<WatchProvider>, DataError.Network>
    suspend fun getRecommendedMovies(movieId: String): Result<List<Media>, DataError.Network>
    suspend fun getTvRecommendedMovies(tvId: String): Result<List<Media>, DataError.Network>
    suspend fun getTvShowDetails(tvId: String): Result<MediaDetails, DataError.Network>
    suspend fun getTvCast(tvId: String): Result<List<Cast>, DataError.Network>
}
