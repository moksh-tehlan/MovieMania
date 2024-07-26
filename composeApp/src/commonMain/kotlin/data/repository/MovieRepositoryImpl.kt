package data.repository

import data.mappers.toMedia
import data.mappers.toMoviesAndShows
import data.model.MoviesAndShowsDto
import data.model.MoviesDto
import data.model.SearchDto
import data.model.TvShowsDto
import data.networking.get
import domain.model.Media
import domain.repository.MovieRepository
import domain.utils.DataError
import domain.utils.Result
import domain.utils.map
import io.ktor.client.HttpClient

class MovieRepositoryImpl(
    private val httpClient: HttpClient,
) : MovieRepository {
    override suspend fun getPopularMovies(): Result<List<Media>, DataError.Network> {
        val result = httpClient.get<MoviesDto>(
            route = "movie/popular",
        )
        return result.map { movies -> movies.results.map { moviesList -> moviesList.toMedia() } }
    }

    override suspend fun getTopRatedMovies(): Result<List<Media>, DataError.Network> {
        val result = httpClient.get<MoviesDto>(
            route = "movie/top_rated",
        )
        return result.map { movies -> movies.results.map { moviesList -> moviesList.toMedia() } }
    }

    override suspend fun getPopularTvShows(): Result<List<Media>, DataError.Network> {
        val result = httpClient.get<TvShowsDto>(
            route = "tv/popular",
        )
        return result.map { tvShows -> tvShows.results.map { tvShowsList -> tvShowsList.toMedia() } }
    }

    override suspend fun getTrending(): Result<List<Media>, DataError.Network> {
        val result = httpClient.get<MoviesAndShowsDto>(
            route = "trending/all/day",
        )
        return result.map { trending ->
            trending.results.map { trendingList ->
                trendingList.toMoviesAndShows()
            }
        }
    }

    override suspend fun searchQuery(query: String): Result<List<Media>, DataError.Network> {
        val result = httpClient.get<SearchDto>(
            route = "search/multi",
            queryParameters = mapOf("query" to query, "include_adult" to true)
        )
        return result.map { search ->
            search.results.map { searchList ->
                searchList.toMedia()
            }
        }
    }
}