package id.pahlevikun.ilt3.sample.preference

import androidx.annotation.ColorRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.pahlevikun.ilt3.sample.preference.data.ILTPreference

class PreferenceViewModel(
    private val preferenceManager: ILTPreference,
) : ViewModel() {

    private val _color: MutableLiveData<Int> = MutableLiveData(-1)
    var color: LiveData<Int> = _color

    init {
        _color.value = preferenceManager.getColor()
    }

    fun updateColor(@ColorRes color: Int) {
        preferenceManager.putColor(color)
        _color.value = preferenceManager.getColor()
    }
}