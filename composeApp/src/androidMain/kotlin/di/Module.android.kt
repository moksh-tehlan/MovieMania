package di

import android.content.Context
import database.DatabaseBuilder
import org.koin.dsl.module

actual val platformModule = module {
    single {
        DatabaseBuilder.getDatabaseBuilder(get<Context>())
    }
}