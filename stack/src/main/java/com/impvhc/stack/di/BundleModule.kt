package com.impvhc.stack.di

import android.app.Application
import android.os.Bundle
import com.impvhc.stack.util.getCurrentActivity
import dagger.Module
import dagger.Provides

@Module
abstract class BundleModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun bundle(app: Application): Bundle = app.getCurrentActivity()!!.intent.extras
                ?: Bundle.EMPTY

    }
}