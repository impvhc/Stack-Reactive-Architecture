package com.impvhc.stack

import com.impvhc.stack.util.lifecycle
import dagger.android.support.DaggerApplication

/**
 * Base application class with support library.
 */
abstract class StackApplicationCompat : DaggerApplication() {

    override fun onCreate() {
        lifecycle()
        super.onCreate()
    }
}