package org.sopt.androidseminar

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleObserver(private val className: String, private val lifecycle: Lifecycle) : LifecycleObserver {

    fun registerLogger() {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i("$className LifeCycleEvent", "ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.i("$className LifeCycleEvent", "ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i("$className LifeCycleEvent", "ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i("$className LifeCycleEvent", "ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.i("$className LifeCycleEvent", "ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.i("$className LifeCycleEvent", "ON_DESTROY")
    }
}