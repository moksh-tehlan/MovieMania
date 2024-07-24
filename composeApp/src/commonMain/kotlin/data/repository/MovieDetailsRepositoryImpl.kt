package data.repository

import data.mappers.toAggregatedCast
import data.mappers.toCast
import data.mappers.toMovieDetails
import data.mappers.toMovies
import data.mappers.toTvShowDetail
import data.mappers.toTvShows
import data.mappers.toWatchProvider
import data.model.AggregatedCastDto
import data.model.CastDto
import data.model.MovieDetailsDto
import data.model.MoviesDto
import data.model.ProviderDto
import data.model.TvShowDetailsDto
import data.model.TvShowsDto
import data.networking.get
import domain.model.AggregatedCast
import domain.model.Cast
import domain.model.MovieDetails
import domain.model.Movies
import domain.model.TvShowDetail
import domain.model.TvShows
import domain.model.WatchProvider
import domain.repository.MovieDetailsRepository
import domain.utils.DataError
import domain.utils.Result
import domain.utils.map
import io.ktor.client.HttpClient

class MovieDetailsRepositoryImpl(
    private val httpClient: HttpClient
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Result<MovieDetails, DataError.Network> {
        val result = httpClient.get<MovieDetailsDto>(
            route = "movie/$movieId",
        )
        return result.map { it.toMovieDetails() }
    }

    override suspend fun getMovieCast(movieId: String): Result<List<Cast>, DataError.Network> {
        val result = httpClient.get<CastDto>(
            route = "movie/$movieId/credits",
        )
        return result.map { castDto ->
            castDto.cast.map { castDtoList ->
                castDtoList.toCast()
            }
        }
    }

    override suspend fun getMovieWatchProvider(movieId: String): Result<List<WatchProvider>, DataError.Network> {
        val result = httpClient.get<ProviderDto>(
            route = "movie/$movieId/watch/providers",
        )
        return result.map { providerDto ->
            providerDto.results.india?.flatRate?.map { providerDtoList ->
                providerDtoList.toWatchProvider()
            } ?: emptyList()
        }
    }

    override suspend fun getTvWatchProvider(tvId: String): Result<List<WatchProvider>, DataError.Network> {
        val result = httpClient.get<ProviderDto>(
            route = "tv/$tvId/watch/providers",
        )
        return result.map { providerDto ->
            providerDto.results.india?.flatRate?.map { providerDtoList ->
                providerDtoList.toWatchProvider()
            } ?: emptyList()
        }
    }

    override suspend fun getRecommendedMovies(movieId: String): Result<List<Movies>, DataError.Network> {
        val result = httpClient.get<MoviesDto>(
            route = "movie/$movieId/recommendations",
        )
        return result.map { moviesDto ->
            moviesDto.results.map { moviesDtoList ->
                moviesDtoList.toMovies()
            }
        }
    }

    override suspend fun getTvRecommendedMovies(tvId: String): Result<List<TvShows>, DataError.Network> {
        val result = httpClient.get<TvShowsDto>(
            route = "tv/$tvId/recommendations",
        )
        return result.map { tvShowDto ->
            tvShowDto.results.map { tvShowDtoList ->
                tvShowDtoList.toTvShows()
            }
        }
    }

    override suspend fun getTvShowDetails(tvId: String): Result<TvShowDetail, DataError.Network> {
        val result = httpClient.get<TvShowDetailsDto>(
            route = "tv/$tvId",
        )
        return result.map { tvShowDto ->
            tvShowDto.toTvShowDetail()
        }
    }

    override suspend fun getTvCast(tvId: String): Result<List<AggregatedCast>, DataError.Network> {
        val result = httpClient.get<AggregatedCastDto>(
            route = "tv/$tvId/aggregate_credits",
        )

        return result.map { aggregatedCastDtoList ->
            aggregatedCastDtoList.cast.map { aggregatedCastDto ->
                aggregatedCastDto.toAggregatedCast()
            }
        }
    }
}