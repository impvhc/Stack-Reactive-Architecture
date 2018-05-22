package com.impvhc.stack.di

import dagger.Module

@Module(includes = arrayOf(StackViewModelModule::class, BundleModule::class))
abstract class StackModule