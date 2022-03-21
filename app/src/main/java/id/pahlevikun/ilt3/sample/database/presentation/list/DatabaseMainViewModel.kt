package id.pahlevikun.ilt3.sample.database.presentation.list

import androidx.lifecycle.ViewModel
import id.pahlevikun.ilt3.sample.database.data.DataRepository

class DatabaseMainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getNotes() = dataRepository.getNotes()

}