package data.repository

import data.mappers.toMovies
import data.mappers.toSearch
import data.mappers.toTrending
import data.mappers.toTvShows
import data.model.MoviesAndShowsDto
import data.model.MoviesDto
import data.model.SearchDto
import data.model.TvShowsDto
import data.networking.get
import domain.model.Movies
import domain.model.MoviesAndShows
import domain.model.Search
import domain.model.TvShows
import domain.repository.MovieRepository
import domain.utils.DataError
import domain.utils.Result
import domain.utils.map
import io.ktor.client.HttpClient

class MovieRepositoryImpl(
    private val httpClient: HttpClient
) : MovieRepository {
    override suspend fun getPopularMovies(): Result<List<Movies>, DataError.Network> {
        val result = httpClient.get<MoviesDto>(
            route = "movie/popular",
        )
        return result.map { movies -> movies.results.map { moviesList -> moviesList.toMovies() } }
    }

    override suspend fun getTopRatedMovies(): Result<List<Movies>, DataError.Network> {
        val result = httpClient.get<MoviesDto>(
            route = "movie/top_rated",
        )
        return result.map { movies -> movies.results.map { moviesList -> moviesList.toMovies() } }
    }

    override suspend fun getPopularTvShows(): Result<List<TvShows>, DataError.Network> {
        val result = httpClient.get<TvShowsDto>(
            route = "tv/popular",
        )
        return result.map { tvShows -> tvShows.results.map { tvShowsList -> tvShowsList.toTvShows() } }
    }

    override suspend fun getTrending(): Result<List<MoviesAndShows>, DataError.Network> {
        val result = httpClient.get<MoviesAndShowsDto>(
            route = "trending/all/day",
        )
        return result.map { trending ->
            trending.results.map { trendingList ->
                trendingList.toTrending()
            }
        }
    }

    override suspend fun searchQuery(query: String): Result<List<Search>, DataError.Network> {
        val result = httpClient.get<SearchDto>(
            route = "search/multi",
            queryParameters = mapOf("query" to query, "include_adult" to true)
        )
        return result.map { search ->
            search.results.map { searchList ->
                searchList.toSearch()
            }
        }
    }
}