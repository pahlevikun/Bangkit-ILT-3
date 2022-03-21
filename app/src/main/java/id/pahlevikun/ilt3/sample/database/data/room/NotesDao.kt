package id.pahlevikun.ilt3.sample.database.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.pahlevikun.ilt3.sample.database.data.entity.NotesEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getNews(): LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NotesEntity)

    @Delete
    suspend fun delete(note: NotesEntity)
}