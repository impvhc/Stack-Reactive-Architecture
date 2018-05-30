package com.impvhc.appstack.di

import android.app.Application
import com.impvhc.appstack.App
import com.impvhc.appstack.di.ui.ActivityModule
import com.impvhc.stack.StackApplicationCompat
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by victor on 2/13/18.
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityModule::class)])
interface AppComponent : AndroidInjector<StackApplicationCompat> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun app(app: Application): Builder
    }

    fun inject(app: App)
}