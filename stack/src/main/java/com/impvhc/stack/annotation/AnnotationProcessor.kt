package com.impvhc.stack.annotation

import com.impvhc.stack.type.ActionVM
import com.impvhc.stack.type.ResultVM
import com.impvhc.stack.type.UiEventVM
import com.impvhc.stack.type.UiModelVM
import com.impvhc.stack.viewmodel.StackViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class AnnotationProcessor {

    companion object : AnkoLogger {

        @Suppress("UNCHECKED_CAST")
        fun injectEventIntoAction(instance: Any): MutableMap<KClass<*>, ObservableTransformer<Any, ActionVM>> {
            val listEventIntoAction: MutableMap<KClass<*>, ObservableTransformer<Any, ActionVM>> = mutableMapOf()

            info { "Processing \"EventIntoAction\" Annotation" }
            val met = instance::class.java.declaredFields
            for (field in met) {
                if (field.isAnnotationPresent(EventIntoAction::class.java)) {
                    field.isAccessible = true
                    val set: EventIntoAction = field.getAnnotation(EventIntoAction::class.java)
                    listEventIntoAction[set.kEvent] = field.get(instance) as ObservableTransformer<Any, ActionVM>
                } else if (field.isAnnotationPresent(EventIntoActionEmptyConstructor::class.java)) {
                    field.isAccessible = true
                    val set: EventIntoActionEmptyConstructor = field.getAnnotation(EventIntoActionEmptyConstructor::class.java)
                    val actionC = set.kAction.primaryConstructor
                    if (actionC != null && actionC.parameters.isEmpty()) {
                        val transformerEmpty = ObservableTransformer<Any, ActionVM> { upstream: Observable<Any> -> upstream.flatMap({ event: Any -> Observable.just(actionC.call()) }) }
                        field.set(instance, transformerEmpty)
                        listEventIntoAction[set.kEvent] = field.get(instance) as ObservableTransformer<Any, ActionVM>
                    }
                }
            }
            return listEventIntoAction
        }

        @Suppress("UNCHECKED_CAST")
        fun injectActionIntoResult(instance: Any): MutableMap<KClass<*>, ObservableTransformer<Any, ResultVM>> {
            val listActionIntoResult: MutableMap<KClass<*>, ObservableTransformer<Any, ResultVM>> = mutableMapOf()

            info { "Processing \"ActionIntoResult\" Annotation" }
            val met = instance::class.java.declaredFields
            for (field in met) {
                if (field.isAnnotationPresent(ActionIntoResult::class.java)) {
                    field.isAccessible = true
                    val set: ActionIntoResult = field.getAnnotation(ActionIntoResult::class.java)
                    listActionIntoResult[set.kAction] = field.get(instance) as ObservableTransformer<Any, ResultVM>
                } else if (field.isAnnotationPresent(ActionIntoResultEmptyConstructor::class.java)) {
                    field.isAccessible = true
                    val set: ActionIntoResultEmptyConstructor = field.getAnnotation(ActionIntoResultEmptyConstructor::class.java)
                    val resultC = set.kResult.primaryConstructor
                    if (resultC != null && resultC.parameters.isEmpty()) {
                        val transformerEmpty = ObservableTransformer<Any, ResultVM> { upstream: Observable<Any> -> upstream.flatMap({ action: Any -> Observable.just(resultC.call()) }) }
                        field.set(instance, transformerEmpty)
                        listActionIntoResult[set.kAction] = field.get(instance) as ObservableTransformer<Any, ResultVM>
                    }
                }
            }
            return listActionIntoResult
        }

        fun callProcessResultFunction(instance: Any, uiModel: UiModelVM, result: ResultVM): UiModelVM {
            val met = instance::class.java.declaredMethods
            for (k in met) {
                if (k.isAnnotationPresent(ProcessResult::class.java)) {
                    k.isAccessible = true
                    val set: ProcessResult = k.getAnnotation(ProcessResult::class.java)
                    if (result::class == set.kResult) {
                        return k.invoke(instance, uiModel, result) as UiModelVM
                    }
                }
            }
            //Unknown result - throw error
            throw IllegalArgumentException("Unknown Result: $result")
        }

        fun getUiModel(instance: Any): UiModelVM {
            val met = instance::class.java.declaredFields
            for (field in met) {
                if (field.isAnnotationPresent(BindUiModel::class.java)) {
                    field.isAccessible = true
                    return field.get(instance) as UiModelVM
                }
            }
            //Unknown result - throw error
            throw IllegalArgumentException("UiModel is not defined or is not a valid UiModelVM class")
        }

        fun getStartEventsObservable(instance: Any): Observable<UiEventVM> {
            val met = instance::class.java.declaredFields
            for (field in met) {
                if (field.isAnnotationPresent(BindInitialEvent::class.java)) {
                    field.isAccessible = true
                    return field.get(instance) as Observable<UiEventVM>
                }
            }
            //Unknown result - throw error
            return Observable.empty()
        }

        fun getViewModel(instance: Any): Observable<UiModelVM> {
            val observable: MutableList<Observable<UiModelVM>> = mutableListOf()
            val met = instance::class.java.declaredFields
            for (field in met) {
                if (field.isAnnotationPresent(BindViewModel::class.java)) {
                    field.isAccessible = true
                    val vm = field.get(instance) as StackViewModel
                    info { "UiModel $vm" }
                    observable.add(vm.getUiModelObservable())
                }
            }

            if (observable.size > 0) {
                return Observable.merge(observable)
            }
            //Unknown result - throw error
            throw IllegalArgumentException("StackViewModel is not defined or is not a valid StackViewModel class")
        }

    }

}