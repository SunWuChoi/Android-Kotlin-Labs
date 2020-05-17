package edu.towson.cosc435.labsapp

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person)
}

fun seedDb(db: PersonDatabase) {
    (1..10).forEach {
        GlobalScope.launch {
            db.personDao().insert(Person(it, "Person $it"))
        }
    }
}

fun getDB(ctx: Context): PersonDatabase {
    val db = Room.databaseBuilder<PersonDatabase>(
        ctx,
        PersonDatabase::class.java,
        "person.db"
    ).build()
    if(db.query("select * from Person", null).count == 0) {
        seedDb(db)
    }
    return db
}

@Database(entities = [Person::class], exportSchema = false, version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}