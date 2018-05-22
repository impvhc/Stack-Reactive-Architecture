package com.impvhc.stack

import com.impvhc.stack.util.lifecycle
import dagger.android.DaggerApplication

/**
 * Base application class.
 */
abstract class StackApplication : DaggerApplication() {
    override fun onCreate() {
        lifecycle()
        super.onCreate()
    }
}