package com.impvhc.appstack.di.ui

import com.impvhc.appstack.ui.detail.DetailActivity
import com.impvhc.appstack.ui.main.MainActivity
import com.impvhc.stack.di.StackModule
import com.impvhc.stack.di.StackVMFactory
import android.os.Bundle
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Regular Dagger configuration
 * Include [StackModule] to inject [StackVMFactory] and [Bundle]
 */
@Module(includes = [(ViewModelsModule::class), (StackModule::class)])
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailActivity(): DetailActivity
}