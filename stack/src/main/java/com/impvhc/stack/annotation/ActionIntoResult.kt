package com.impvhc.stack.annotation

import com.impvhc.stack.type.ActionVM
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ActionIntoResult(val kAction: KClass<out ActionVM>)