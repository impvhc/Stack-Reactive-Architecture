package com.impvhc.stack.fragment

import com.impvhc.stack.annotation.AnnotationProcessor
import com.impvhc.stack.type.UiModelVM
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

abstract class StackFragmentCompat : DaggerFragment(), AnkoLogger {
    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        info { "UiModel: subscribe from fragment" }
        compositeDisposable.add(AnnotationProcessor.getViewModel(this)
                .subscribe { t: UiModelVM -> processUiModel(t) })
    }

    override fun onStop() {
        info { "UiModel: unsubscribe from fragment" }
        compositeDisposable.clear()
        super.onStop()
    }

    abstract fun processUiModel(uiModelVM: UiModelVM)
}