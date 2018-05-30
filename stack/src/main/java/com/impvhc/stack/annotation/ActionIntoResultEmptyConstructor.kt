package com.impvhc.stack.annotation

import com.impvhc.stack.type.ActionVM
import com.impvhc.stack.type.ResultVM
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ActionIntoResultEmptyConstructor(val kAction: KClass<out ActionVM>, val kResult: KClass<out ResultVM>)