package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.dao.MediaDao
import data.entity.MediaEntity

@Database(entities = [MediaEntity::class], version = 3)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}