package com.impvhc.appstack.ui.main

import com.impvhc.stack.annotation.*
import com.impvhc.stack.viewmodel.StackViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * extend [StackViewModel]
 */
class MainViewModel
@Inject constructor() : StackViewModel() {

    override fun getViewModelInstance(): StackViewModel {
        return this
    }

    /**
     * Provide our UiModelVM
     * [BindUiModel] bind to [StackViewModel]
     */
    @BindUiModel
    val model = MainModel()

    /**
     * Provide the initial event when the UI is ready
     * [BindInitialEvent] bind to [StackViewModel]
     */
    @BindInitialEvent
    val initialEvent = Observable.just(Events.SaveName("guest"))

    /**
     * Setup the transformer to Event into Action
     * [EventIntoAction] bind to [StackViewModel], Event KClass is required to match the type
     */
    @EventIntoAction(Events.SaveName::class)
    val transformerSaveName = ObservableTransformer<Events.SaveName, Actions.SaveName> { upstream ->
        upstream.flatMap(
                { event -> Observable.just(Actions.SaveName(event.name)) })
    }


    /**
     * Setup the transformer to Event into Action
     * [EventIntoAction] bind to [StackViewModel], Action KClass is required to match the type
     */
    @ActionIntoResult(Actions.SaveName::class)
    val transformerUpdateName = ObservableTransformer<Actions.SaveName, Results.UpdateName> { upstream ->
        upstream.flatMap(
                { action -> updateName(action.name) })
    }

    private fun updateName(name: String): Observable<Results.UpdateName>? {
        return Observable.just(Results.UpdateName(name))
    }

    /**
     * Process the result and update the model
     * [ProcessResult] bind to [StackViewModel], Result KClass is required to match the type
     * @param uiModel - model to update
     * @param result - results from [transformerUpdateName]
     * @return new updated [MainModel]
     */
    @ProcessResult(Results.UpdateName::class)
    fun processUpdateName(uiModel: MainModel, result: Results.UpdateName): MainModel {
        uiModel.login = false
        if (!result.name.isEmpty()) {
            uiModel.name = "Welcome ${result.name.capitalize()}"
            if (result.name != "guest") {
                uiModel.login = true
            }
        }
        return uiModel
    }

    init {
        /**
         * Bind need to be called always on init
         * Set up the PublishRelay with the Model, UiEvent, Actions and Results
         */
        bind()
    }

}