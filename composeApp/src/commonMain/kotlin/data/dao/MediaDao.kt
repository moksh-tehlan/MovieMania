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

    @Query("DELETE FROM MediaEntity WHERE id = :id")
    suspend fun deleteMedia(id: Long)

    @Query("SELECT EXISTS(SELECT * FROM MediaEntity WHERE id = :id)")
    suspend fun mediaExists(id: Long): Boolean

    @Query("SELECT * FROM MediaEntity")
    fun getAllMedia(): Flow<List<MediaEntity>>

}