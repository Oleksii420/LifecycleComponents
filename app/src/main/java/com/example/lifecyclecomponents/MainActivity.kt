package com.example.lifecyclecomponents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val audioPlayer = AudioPlayer()
    private val analyticsTracker = AnalyticsTracker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        UserTracker.register(this)
        Timber.d("Зареєстровано слухачів у UserTracker: ${UserTracker.getListenersCount()}")
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnOpenSecond).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycle.addObserver(audioPlayer)
        lifecycle.addObserver(analyticsTracker)
    }
}