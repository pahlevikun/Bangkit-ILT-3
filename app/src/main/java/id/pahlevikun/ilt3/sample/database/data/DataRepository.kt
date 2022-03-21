package id.pahlevikun.ilt3.sample.database.data

import androidx.lifecycle.LiveData
import id.pahlevikun.ilt3.sample.database.data.entity.NotesEntity
import id.pahlevikun.ilt3.sample.database.data.room.NotesDao

class DataRepository private constructor(private val notesDao: NotesDao) {

    fun getNotes(): LiveData<List<NotesEntity>> = notesDao.getNews()

    suspend fun deleteNote(note: NotesEntity) = notesDao.delete(note)

    suspend fun insertNote(notes: NotesEntity) {
        notesDao.insertNote(notes)
    }

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            notesDao: NotesDao,
        ): DataRepository =
            instance ?: synchronized(this) { instance ?: DataRepository(notesDao) }
                .also { instance = it }
    }
}