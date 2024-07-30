package database

import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.MovieDataBase

fun getDatabaseBuilder(): RoomDatabase.Builder<MovieDataBase> {
    val dbFilePath = NSHomeDirectory()+"/my_room.db"
    return Room.databaseBuilder<MovieDataBase>(
        name = dbFilePath,
        factory = { MovieDataBase::class..instantiateImpl() }
    )
}
