package edu.towson.cosc435.labsapp.database

import androidx.room.*
import edu.towson.cosc435.labsapp.models.Song
import java.util.*

@Dao
interface SongDao {
    @Insert
    suspend fun addSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Delete
    suspend fun deleteSong(song: Song)

    @Query("select id, name, artist, track_num, is_awesome from Song")
    suspend fun getAllSongs(): List<Song>
}

class UUIDConverter {
    @TypeConverter
    fun fromString(uuid: String): UUID {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun toString(uuid: UUID): String {
        return uuid.toString()
    }
}

@Database(entities = arrayOf(Song::class), version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}