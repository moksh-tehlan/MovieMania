package domain.repository

import domain.model.Media
import domain.utils.DataError
import domain.utils.Result

interface MovieRepository {
    suspend fun getPopularMovies(): Result<List<Media>,DataError.Network>
    suspend fun getTopRatedMovies(): Result<List<Media>,DataError.Network>
    suspend fun getPopularTvShows(): Result<List<Media>,DataError.Network>
    suspend fun getTrending(): Result<List<Media>,DataError.Network>
    suspend fun searchQuery(query:String): Result<List<Media>, DataError.Network>
}