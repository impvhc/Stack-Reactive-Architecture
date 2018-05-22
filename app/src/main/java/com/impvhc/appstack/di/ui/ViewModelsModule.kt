package com.impvhc.appstack.di.ui

import android.arch.lifecycle.ViewModel
import com.impvhc.appstack.ui.detail.DetailViewModel
import com.impvhc.appstack.ui.main.MainViewModel
import com.impvhc.stack.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun providesDetailsViewModel(detailsViewModel: DetailViewModel): ViewModel
}