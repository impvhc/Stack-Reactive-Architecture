package com.impvhc.stack

import com.impvhc.stack.extension.lifecycle
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