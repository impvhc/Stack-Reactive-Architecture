package com.impvhc.appstack.ui.detail

import com.impvhc.stack.type.ActionVM
import com.impvhc.stack.type.ResultVM
import com.impvhc.stack.type.UiEventVM
import com.impvhc.stack.type.UiModelVM

data class DetailState(
        var name: String = ""
) : UiModelVM()

sealed class Events {
    data class SetName(val name: String) : UiEventVM()
}

sealed class Actions {
    data class SetName(val name: String) : ActionVM()
}

sealed class Results {
    data class SetName(val name: String) : ResultVM()
}