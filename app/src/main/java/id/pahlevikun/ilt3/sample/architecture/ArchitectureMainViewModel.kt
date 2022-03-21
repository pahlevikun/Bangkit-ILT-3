package id.pahlevikun.ilt3.sample.architecture

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ArchitectureMainViewModel : ViewModel() {

    private val _data = MutableLiveData(0)
    var data = _data

    init {
        viewModelScope.launch {
            Handler(Looper.getMainLooper()).postDelayed({
                update()
            }, 5000L)
        }
    }

    fun test(): String = "Test rotate ke ${_data.value}"

    fun update() {
        val currentValue = (_data.value ?: 0)
        _data.value = currentValue + 1
    }
}
