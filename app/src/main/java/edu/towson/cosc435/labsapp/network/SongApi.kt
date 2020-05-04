package edu.towson.cosc435.labsapp.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.net.URL
import java.util.*

interface ISongApi {
    suspend fun fetchSongs(): Deferred<List<Song>>
    suspend fun fetchIcon(iconUrl: String): Deferred<Bitmap>
}

class SongApi(val controller: ISongController): ISongApi {

    private val BASE_URL: String = "https://my-json-server.typicode.com/rvalis-towson/lab_api/songs"
    private val client: OkHttpClient = OkHttpClient()

    override suspend fun fetchSongs(): Deferred<List<Song>> {
        return controller.async(Dispatchers.IO) {
            val request = Request.Builder()
                .url(BASE_URL)
                .get()
                .build()
            val result: String? = client.newCall(request).execute().body?.string()
            val songs: List<Song> = parseJson(result)
            songs
        }
    }

    override suspend fun fetchIcon(iconUrl: String): Deferred<Bitmap> {
        return controller.async(Dispatchers.IO) {
            val filename = getImageFilename(iconUrl)
            val bitmap = controller.checkCache(filename)
            if(bitmap != null) {
                bitmap
            } else {
                val request = Request.Builder()
                    .url(iconUrl)
                    .get()
                    .build()
                val stream = client.newCall(request).execute().body?.byteStream()
                val bitmap = BitmapFactory.decodeStream(stream)
                if(bitmap != null)
                controller.cacheIcon(filename, bitmap)
                bitmap
            }
        }
    }

    private fun parseJson(json: String?): List<Song> {
        val songs = mutableListOf<Song>()
        if(json == null) return songs
        val jsonArr = JSONArray(json)
        var i = 0
        while(i < jsonArr.length()) {
            val jsonObj = jsonArr.getJSONObject(i)
            val song = Song(
                id = UUID.fromString(jsonObj.getString("id")),
                artist = jsonObj.getString("artist"),
                name = jsonObj.getString("name"),
                trackNum = jsonObj.getInt("track_num"),
                isAwesome = jsonObj.getBoolean("is_awesome"),
                iconUrl = jsonObj.getString("icon_url")
            )
            songs.add(song)
            i++
        }
        return songs
    }

    private fun getImageFilename(url: String): String {
        val urlObj = URL(url)
        val query = urlObj.query
        val filename = query.replace("=", "")
        System.out.println(filename.plus(".jpg"))
        return filename.plus(".jpg")
    }
}