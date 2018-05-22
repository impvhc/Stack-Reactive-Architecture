package com.impvhc.appstack.di

import android.app.Application
import com.impvhc.appstack.AppSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by victor on 2/13/18.
 */
@Module
internal class AppModule {

    @Singleton
    @Provides
    fun providesAppSharedPreferences(app: Application): AppSharedPreferences {
        return AppSharedPreferences(app)
    }
}