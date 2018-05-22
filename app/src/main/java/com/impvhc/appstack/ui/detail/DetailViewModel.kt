package com.impvhc.appstack.ui.detail

import android.os.Bundle
import com.impvhc.stack.component.StackViewModel
import org.jetbrains.anko.info
import javax.inject.Inject

class DetailViewModel
@Inject constructor(extras: Bundle) : StackViewModel() {
    init {
        info {
            extras
        }
        info {
            extras.getInt("TEST")
        }
    }
}