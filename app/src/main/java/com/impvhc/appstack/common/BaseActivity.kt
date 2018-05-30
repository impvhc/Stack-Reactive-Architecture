package com.impvhc.appstack.common

import com.impvhc.stack.activity.StackActivityCompat
import io.reactivex.disposables.CompositeDisposable

/**
 * Use [StackActivityCompat]
 */
abstract class BaseActivity : StackActivityCompat() {
    val compositeDisposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}