package edu.towson.cosc435.labsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Song (
    @PrimaryKey
    val id: UUID,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "artist")
    val artist: String,
    @ColumnInfo(name = "track_num")
    val trackNum: Int,
    @ColumnInfo(name = "is_awesome")
    val isAwesome: Boolean
)