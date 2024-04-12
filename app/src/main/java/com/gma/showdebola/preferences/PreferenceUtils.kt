package com.gma.showdebola.preferences

import android.content.Context
import android.content.SharedPreferences
import com.gma.showdebola.application.AppApplication


    private fun getPreferences() : SharedPreferences {
        return AppApplication.appContext.getSharedPreferences(
            PreferenceKeys.PREFERENCES_FILE.toString(),
            Context.MODE_PRIVATE
        )
    }

    fun getPreferenceString(key: PreferenceKeys, default: String? = null) : String? {
        return getPreferences()
            .getString(key.toString(), default)
    }

    fun putPreference(key: PreferenceKeys, newValue: String) {
        with (getPreferences().edit()) {
            putString(key.toString(), newValue)
            apply()
        }
    }

