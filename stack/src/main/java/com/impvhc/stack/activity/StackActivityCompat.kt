package com.impvhc.stack.activity

import com.impvhc.stack.annotation.AnnotationProcessor
import com.impvhc.stack.type.UiModelVM
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by victor on 2/13/18.
 */
abstract class StackActivityCompat : DaggerAppCompatActivity(), AnkoLogger {

    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        info { "UiModel: subscribe" }
        compositeDisposable.add(AnnotationProcessor.getViewModel(this)
                .subscribe { t: UiModelVM -> processUiModel(t) })
    }

    override fun onStop() {
        super.onStop()
        info { "UiModel: unsubscribe" }
        compositeDisposable.clear()
    }

    abstract fun processUiModel(uiModelVM: UiModelVM)
}