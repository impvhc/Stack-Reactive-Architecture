package com.impvhc.stack.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by victor on 2/13/18.
 */
@Module
abstract class StackViewModelModule {

    @Binds
    internal abstract fun bindCoreVMFactory(factory: StackVMFactory): ViewModelProvider.Factory
}