package di

import data.database.MovieDataBase
import data.networking.HttpClientFactory
import data.repository.MovieDetailsRepositoryImpl
import data.repository.MovieRepositoryImpl
import database.getRoomDatabase
import domain.repository.MovieDetailsRepository
import domain.repository.MovieRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.bookmark.viewmodel.BookmarkViewModel
import presentation.detail.viewmodel.DetailScreenViewModel
import presentation.listing.viewmodel.ListingScreenViewModel
import presentation.search.viewmodel.SearchScreenViewModel


expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory().build()
    }
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
    singleOf(::MovieDetailsRepositoryImpl).bind<MovieDetailsRepository>()
    single<MovieDataBase> {
        getRoomDatabase(get())
    }
    single {
        get<MovieDataBase>().mediaDao()
    }
    viewModelOf(::ListingScreenViewModel)
    viewModelOf(::SearchScreenViewModel)
    viewModelOf(::DetailScreenViewModel)
    viewModelOf(::BookmarkViewModel)
}