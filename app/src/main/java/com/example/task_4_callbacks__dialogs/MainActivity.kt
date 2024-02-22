package com.example.task_4_callbacks__dialogs

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import com.example.task_4_callbacks__dialogs.MainScreen.MainScreen

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.insetsController?.hide(WindowInsets.Type.statusBars())
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
            finish()
        }, ProjectConstants.TIMER_FOR_SPLASH.toLong())


    }
}