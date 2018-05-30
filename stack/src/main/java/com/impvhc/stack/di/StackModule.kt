package com.impvhc.stack.di

import android.os.Bundle
import dagger.Module

/**
 * Include [StackViewModelModule] to bind [StackVMFactory]
 * Include [BundleModule] to bind [Bundle]
 */
@Module(includes = [(StackViewModelModule::class), (BundleModule::class)])
abstract class StackModule