package data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MediaEntity(
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    val overview: String,
    val title: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    @ColumnInfo(name = "is_bookmarked")
    val isBookmarked: Boolean,
)