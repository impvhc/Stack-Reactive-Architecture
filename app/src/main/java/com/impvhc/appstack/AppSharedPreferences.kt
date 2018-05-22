package com.impvhc.appstack

import android.content.Context

/**
 * Created by victor on 2/13/18.
 */
class AppSharedPreferences(context: Context) {
    private val SHARED_PREFERENCES = context.packageName + ".SHARED_PREFS"

    private val mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    fun getSharePreferencesName(): String {
        return SHARED_PREFERENCES
    }
}