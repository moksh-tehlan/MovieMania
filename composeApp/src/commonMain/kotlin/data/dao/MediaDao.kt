package data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.entity.MediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(mediaEntity: MediaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mediaEntities: List<MediaEntity>)

    @Query("SELECT * FROM MediaEntity")
    fun getAllMedia(): Flow<List<MediaEntity>>

}