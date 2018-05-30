package com.impvhc.stack.annotation

import com.impvhc.stack.type.ActionVM
import com.impvhc.stack.type.UiEventVM
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventIntoActionEmptyConstructor(val kEvent: KClass<out UiEventVM>, val kAction: KClass<out ActionVM>)