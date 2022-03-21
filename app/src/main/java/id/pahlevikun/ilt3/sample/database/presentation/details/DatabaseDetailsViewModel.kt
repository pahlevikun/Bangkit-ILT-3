package id.pahlevikun.ilt3.sample.database.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.pahlevikun.ilt3.sample.database.data.DataRepository
import id.pahlevikun.ilt3.sample.database.data.entity.NotesEntity
import kotlinx.coroutines.launch

class DatabaseDetailsViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun saveNote(item: NotesEntity) {
        viewModelScope.launch { dataRepository.insertNote(item) }
    }

    fun deleteNote(item: NotesEntity) {
        viewModelScope.launch { dataRepository.deleteNote(item) }
    }

}