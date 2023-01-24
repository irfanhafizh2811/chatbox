package com.irfan.chatbox.pref

import android.content.SharedPreferences

class CorePref(private val sharedPreference: SharedPreferences) {

    fun getString(key: String?, defValue: String?): String? {
        return sharedPreference.getString(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int {
        return sharedPreference.getInt(key, defValue)
    }

    fun setString(key: String?, value: String?) {
        sharedPreference.edit().putString(key, value).apply()
    }

    fun setInt(key: String?, value: Int) {
        sharedPreference.edit().putInt(key, value).apply()
    }

    fun setBoolean(key: String?, value: Boolean) {
        sharedPreference.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreference.getBoolean(key, defValue)
    }

    fun setLong(key: String?, value: Long) {
        sharedPreference.edit().putLong(key, value).apply()
    }

    fun getLong(key: String?, defValue: Long): Long {
        return sharedPreference.getLong(key, defValue)
    }

    fun remove(key: String?) {
        sharedPreference.edit().remove(key).apply()
    }

    fun clear() {
        sharedPreference.edit().clear().commit()
    }

    fun hasKey(key: String?): Boolean {
        return sharedPreference.contains(key)
    }
}