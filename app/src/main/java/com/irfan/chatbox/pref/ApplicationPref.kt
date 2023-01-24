package com.irfan.chatbox.pref

class ApplicationPref(val corePref: CorePref){

    companion object {
        private const val SYNC_DATA = "SYNC_DATA"
        private const val USER_ID = "USER_ID"
    }

    var userId: String
        set(value) = corePref.setString(USER_ID, value)
        get() = corePref.getString(USER_ID, "").orEmpty()

    var hasSyncData: Boolean
        set(value) = corePref.setBoolean(SYNC_DATA, value)
        get() = corePref.getBoolean(SYNC_DATA, false)
}