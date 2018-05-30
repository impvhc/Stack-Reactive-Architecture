package com.impvhc.stack.viewmodel

import android.arch.lifecycle.ViewModel
import com.impvhc.stack.annotation.AnnotationProcessor
import com.impvhc.stack.type.ActionVM
import com.impvhc.stack.type.ResultVM
import com.impvhc.stack.type.UiEventVM
import com.impvhc.stack.type.UiModelVM
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

abstract class StackViewModel : ViewModel(), AnkoLogger {

    private val publishRelayUiEvents = PublishRelay.create<UiEventVM>()

    private lateinit var uiModelObservable: Observable<UiModelVM>

    protected fun getSchedulers(): Scheduler {
        return Schedulers.computation()
    }

    fun getUiModelObservable(): Observable<UiModelVM> {
        return uiModelObservable
    }

    /**
     * Bind to [PublishRelay].
     */
    fun bind() {
        uiModelObservable = publishRelayUiEvents
                .observeOn(getSchedulers())
                //Merge with start events
                .mergeWith(AnnotationProcessor.getStartEventsObservable(getViewModelInstance()))
                //Translate UiEvents into Actions
                .compose(getTransformEventsIntoActions())
                //Asynchronous Actions To Interactor
                .publish({ t: Observable<ActionVM> -> processAction(t) })
                //Scan Results to Update BindUiModel
                .scan(AnnotationProcessor.getUiModel(getViewModelInstance()), { t1: UiModelVM, t2: ResultVM -> AnnotationProcessor.callProcessResultFunction(getViewModelInstance(), t1, t2) })
                //Publish results to main thread.
                .observeOn(AndroidSchedulers.mainThread())
                //Save history for late subscribers.
                .replay(1)
                .autoConnect()
    }

    private fun getTransformEventsIntoActions(): ObservableTransformer<UiEventVM, ActionVM> {
        return ObservableTransformer { upstream -> upstream.publish { t: Observable<UiEventVM> -> info { "getTransformEventsIntoActions" }; return@publish Observable.merge(getTransformEventsIntoActionsList(t)) } }
    }

    private fun getTransformActionsIntoResults(): ObservableTransformer<ActionVM, ResultVM> {
        return ObservableTransformer { upstream -> upstream.publish { t: Observable<ActionVM> -> info { "getTransformActionsIntoResults" }; return@publish Observable.merge(getTransformActionsIntoResultsList(t)) } }
    }

    private fun processAction(action: Observable<ActionVM>): Observable<ResultVM> {
        return action.compose(getTransformActionsIntoResults())
    }

    private fun getTransformEventsIntoActionsList(t: Observable<UiEventVM>): MutableList<Observable<ActionVM>> {
        val list: MutableList<Observable<ActionVM>> = arrayListOf()
        AnnotationProcessor.injectEventIntoAction(getViewModelInstance()).forEach { kEvent, observer ->
            list.add(t.ofType(kEvent.java).compose(observer))
        }
        return list
    }

    private fun getTransformActionsIntoResultsList(t: Observable<ActionVM>): MutableList<Observable<ResultVM>> {
        val list: MutableList<Observable<ResultVM>> = arrayListOf()
        AnnotationProcessor.injectActionIntoResult(getViewModelInstance()).forEach { kAction, observer ->
            list.add(t.ofType(kAction.java).compose(observer))
        }
        return list
    }

    /**
     * Process events from the UI.
     * @param uiEvent - [UiEventVM]
     */
    fun processUiEvent(uiEvent: UiEventVM) {
        if (uiModelObservable == null) {
            throw IllegalStateException("Model Observer not ready. Did you forget to call bind()?")
        }
        info { "processUiEvent" }
        info { uiEvent }
        publishRelayUiEvents.accept(uiEvent)
    }

    abstract fun getViewModelInstance(): StackViewModel


}