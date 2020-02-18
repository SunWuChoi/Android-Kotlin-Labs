package edu.towson.cosc435.labsapp.models

data class Song(
    val name: String,
    val artist: String,
    val album: String,
    val trackNumber: Int,
    val isAwesome: Boolean
)