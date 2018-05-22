package com.impvhc.appstack

import com.impvhc.appstack.di.DaggerAppComponent
import com.impvhc.stack.StackApplicationCompat
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : StackApplicationCompat() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .app(this)
                .build()

        appComponent.inject(this)

        return appComponent
    }
}