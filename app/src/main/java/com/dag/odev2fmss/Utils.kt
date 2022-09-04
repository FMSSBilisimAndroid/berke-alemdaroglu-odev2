package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

object Util {
    fun backStack(applicationContext: Context, activity: AppCompatActivity) {
        val openIntent = Intent(applicationContext, activity::class.java)
        openIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(openIntent)
    }
}
