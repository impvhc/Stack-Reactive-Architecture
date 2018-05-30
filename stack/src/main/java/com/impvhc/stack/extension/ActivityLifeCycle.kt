package com.impvhc.stack.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 * Created by victor on 3/28/18.
 */

@SuppressLint("StaticFieldLeak")
var currentActivity: Activity? = null

fun Application.lifecycle() = registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        Log.i("LifeCycle", "onActivityCreated/" + p0!!.javaClass.canonicalName)
        currentActivity = p0
    }

    override fun onActivityResumed(p0: Activity?) {
        Log.i("LifeCycle", "onActivityResumed/" + p0!!.javaClass.canonicalName)
        currentActivity = p0
    }

    override fun onActivityPaused(p0: Activity?) {
        Log.i("LifeCycle", "onActivityPaused/" + p0!!.javaClass.canonicalName)
        currentActivity = null
        super.onActivityDestroyed(p0)
    }
})

fun Application.getCurrentActivity() = currentActivity

abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(p0: Activity?) {
    }

    override fun onActivityResumed(p0: Activity?) {
    }

    override fun onActivityStarted(p0: Activity?) {
    }

    override fun onActivityDestroyed(p0: Activity?) {
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
    }
}