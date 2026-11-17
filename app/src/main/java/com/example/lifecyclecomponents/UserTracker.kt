package com.example.lifecyclecomponents

import android.content.Context

object UserTracker {
    private val listeners = mutableListOf<Context>()

    fun register(context: Context) {
        listeners.add(context.applicationContext)
    }

    fun getListenersCount(): Int = listeners.size
}