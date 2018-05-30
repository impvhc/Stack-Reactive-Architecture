package com.impvhc.appstack.ui.detail

import android.os.Bundle
import com.impvhc.stack.annotation.*
import com.impvhc.stack.type.UiModelVM
import com.impvhc.stack.viewmodel.StackViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * extend [StackViewModel]
 */
class DetailViewModel
@Inject constructor(extras: Bundle) : StackViewModel() {

    override fun getViewModelInstance(): StackViewModel {
        return this
    }

    @BindInitialEvent
    val setLoginName = Observable.just(Events.SetName(extras.getString("login_name")))

    @EventIntoAction(Events.SetName::class)
    val eventLoginName = ObservableTransformer<Events.SetName, Actions.SetName> { upstream -> upstream.flatMap({ restoreEvent -> Observable.just(Actions.SetName(restoreEvent.name)) }) }

    @ActionIntoResult(Actions.SetName::class)
    val actionLoginName = ObservableTransformer<Actions.SetName, Results.SetName> { upstream -> upstream.flatMap({ restoreEvent -> Observable.just(Results.SetName(restoreEvent.name)) }) }

    @BindUiModel
    val state = DetailState()

    @ProcessResult(Results.SetName::class)
    fun processNameResult(uiModel: DetailState, result: Results.SetName): UiModelVM {
        uiModel.name = result.name
        return uiModel
    }

    init {
        bind()
    }
}