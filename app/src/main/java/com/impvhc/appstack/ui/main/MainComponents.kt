package com.impvhc.appstack.ui.main

import com.impvhc.stack.type.ActionVM
import com.impvhc.stack.type.ResultVM
import com.impvhc.stack.type.UiEventVM
import com.impvhc.stack.type.UiModelVM

data class MainModel(
        var name: String = "",
        var login: Boolean = false
) : UiModelVM()

sealed class Events {
    data class SaveName(val name: String) : UiEventVM()
}

sealed class Actions {
    data class SaveName(val name: String) : ActionVM()
}

sealed class Results {
    data class UpdateName(val name: String) : ResultVM()
}