package com.irfan.chatbox

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.irfan.chatbox.util.dateFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DateFormatTest {

    @Test
    @Throws(Exception::class)
    fun TextView.textViewDateFormatter_isCorrect() {
        val date = "2022-11-11T10:45:00.000Z"
        assertEquals("2022-11-11", dateFormatter(date))
    }

}