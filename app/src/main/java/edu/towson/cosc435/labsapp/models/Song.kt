package edu.towson.cosc435.labsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
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
    @SerializedName("track_num")
    val trackNum: Int,
    @ColumnInfo(name = "is_awesome")
    @SerializedName("is_awesome")
    val isAwesome: Boolean,
    @ColumnInfo(name = "icon_url")
    @SerializedName("icon_url")
    val iconUrl: String
)
