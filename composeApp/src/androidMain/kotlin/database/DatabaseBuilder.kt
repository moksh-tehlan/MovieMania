package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.MovieDataBase

object DatabaseBuilder {
    fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<MovieDataBase> {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("MovieDb.db")
        return Room.databaseBuilder<MovieDataBase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}