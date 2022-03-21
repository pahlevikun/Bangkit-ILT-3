package id.pahlevikun.ilt3.sample.database.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.pahlevikun.ilt3.sample.database.data.entity.NotesEntity

@Database(
    entities = [NotesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ILTDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var instance: ILTDatabase? = null

        fun getInstance(context: Context): ILTDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ILTDatabase::class.java, "ilt3.db"
                ).build()
            }
    }
}