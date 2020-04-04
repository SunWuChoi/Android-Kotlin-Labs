package edu.towson.cosc435.labsapp.database

import androidx.room.*
import edu.towson.cosc435.labsapp.models.Song
import java.util.*

// TODO - 1. Make every method in SongDao a 'suspend' method
@Dao
interface SongDao {
    @Insert
    fun addSong(song: Song)

    @Update
    fun updateSong(song: Song)

    @Delete
    fun deleteSong(song: Song)

    @Query("select id, name, artist, track_num, is_awesome from Song")
    fun getAllSongs(): List<Song>
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