package domain.repository

import domain.model.Movies
import domain.model.MoviesAndShows
import domain.model.Search
import domain.model.TvShows
import domain.utils.DataError
import domain.utils.Result

interface MovieRepository {
    suspend fun getPopularMovies(): Result<List<Movies>,DataError.Network>
    suspend fun getTopRatedMovies(): Result<List<Movies>,DataError.Network>
    suspend fun getPopularTvShows(): Result<List<TvShows>,DataError.Network>
    suspend fun getTrending(): Result<List<MoviesAndShows>,DataError.Network>
    suspend fun searchQuery(query:String): Result<List<Search>, DataError.Network>
}