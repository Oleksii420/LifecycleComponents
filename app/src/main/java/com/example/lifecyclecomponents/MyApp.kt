package com.example.lifecyclecomponents

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import timber.log.Timber

class MyApp : Application() {

    val backgroundDetector = BackgroundDetector()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("MyApp.onCreate")

        //registerActivityLifecycleCallbacks(backgroundDetector)
        ProcessLifecycleOwner.get().lifecycle.addObserver(backgroundDetector)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            private fun log(event: String, activity: Activity) {
                val state = (activity as? LifecycleOwner)?.lifecycle?.currentState
                Timber.d("$event: ${activity.javaClass.simpleName}, стан = $state")
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) =
                log("onActivityCreated", activity)

            override fun onActivityStarted(activity: Activity) = log("onActivityStarted", activity)
            override fun onActivityResumed(activity: Activity) = log("onActivityResumed", activity)
            override fun onActivityPaused(activity: Activity) = log("onActivityPaused", activity)
            override fun onActivityStopped(activity: Activity) = log("onActivityStopped", activity)
            override fun onActivityDestroyed(activity: Activity) = log("onActivityDestroyed", activity)

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        })
    }
}