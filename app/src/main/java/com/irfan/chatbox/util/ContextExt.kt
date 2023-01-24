package com.irfan.chatbox.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import com.irfan.chatbox.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Throws(RuntimeException::class)
fun Context.readJsonAssetToString(fileName: String): String {
    val builder = StringBuilder()
    try {
        val inputStream = this.assets.open(fileName)
        val buffer = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

        var str = buffer.readLine()
        while (str != null) {
            builder.append(str)
            str = buffer.readLine()
        }

        buffer.close()
        inputStream.close()
        return builder.toString()
    } catch (e: IOException) {
        e.printStackTrace()
        throw RuntimeException(e)
    }
}

fun Activity.isTabletScreenLandscape(): Boolean {
    return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            && resources.getBoolean(R.bool.isTablet)
}