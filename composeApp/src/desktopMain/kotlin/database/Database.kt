package database

import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.MovieDataBase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<MovieDataBase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<MovieDataBase>(
        name = dbFile.absolutePath,
    )
}