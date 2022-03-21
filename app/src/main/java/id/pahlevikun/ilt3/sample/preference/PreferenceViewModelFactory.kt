package id.pahlevikun.ilt3.sample.preference

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.pahlevikun.ilt3.sample.preference.data.ILTPreference

class PreferenceViewModelFactory private constructor(
    private val preferenceManager: ILTPreference,
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
            return PreferenceViewModel(preferenceManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {

        @Volatile
        private var instance: PreferenceViewModelFactory? = null

        fun getInstance(context: Context): PreferenceViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PreferenceViewModelFactory(
                    preferenceManager = ILTPreference(context)
                )
            }.also { instance = it }
    }
}