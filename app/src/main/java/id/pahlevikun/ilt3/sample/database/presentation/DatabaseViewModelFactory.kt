package id.pahlevikun.ilt3.sample.database.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.pahlevikun.ilt3.sample.database.data.DataRepository
import id.pahlevikun.ilt3.sample.database.data.room.ILTDatabase

class DatabaseViewModelFactory private constructor(
    private val dataRepository: DataRepository,
) : ViewModelProvider.NewInstanceFactory() {

    private var clazz: Class<out ViewModel>? = null

    fun setClass(clazz: Class<out ViewModel>) {
        this.clazz = clazz
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (clazz == null) throw IllegalArgumentException("Clazz is null")
        if (modelClass.isAssignableFrom(clazz)) {
            return clazz!!.getConstructor(DataRepository::class.java).newInstance(dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    object Injection {
        fun provideRepository(context: Context): DataRepository {
            val database = ILTDatabase.getInstance(context)
            val dao = database.notesDao()
            return DataRepository.getInstance(dao)
        }
    }

    companion object {

        @Volatile
        private var instance: DatabaseViewModelFactory? = null

        fun getInstance(context: Context, clazz: Class<out ViewModel>) =
            (instance ?: synchronized(this) {
                instance ?: DatabaseViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }).apply { setClass(clazz) }
    }
}