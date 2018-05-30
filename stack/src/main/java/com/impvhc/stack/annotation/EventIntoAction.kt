package com.impvhc.stack.annotation

import com.impvhc.stack.type.UiEventVM
import kotlin.reflect.KClass


@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventIntoAction(val kEvent: KClass<out UiEventVM>)