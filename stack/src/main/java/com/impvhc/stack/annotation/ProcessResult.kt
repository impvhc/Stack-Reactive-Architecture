package com.impvhc.stack.annotation

import com.impvhc.stack.type.ResultVM
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ProcessResult(val kResult: KClass<out ResultVM>)