package com.impvhc.appstack.di.ui

import com.impvhc.appstack.ui.detail.DetailActivity
import com.impvhc.appstack.ui.main.MainActivity
import com.impvhc.stack.di.StackModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(ViewModelsModule::class, StackModule::class))
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailActivity(): DetailActivity
}