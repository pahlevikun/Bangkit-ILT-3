package id.pahlevikun.ilt3.sample.preference.data

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.ColorRes
import androidx.preference.PreferenceManager

class ILTPreference(context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun putColor(@ColorRes color: Int) {
        getEditor().putInt(KEY_COLOR, color).apply()
    }

    @ColorRes
    fun getColor(): Int {
        return sharedPreferences.getInt(KEY_COLOR, -1)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    companion object {
        private const val KEY_COLOR = "ilt3.key.color"
    }

}