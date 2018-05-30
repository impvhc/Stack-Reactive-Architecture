package com.impvhc.appstack.ui.detail

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.impvhc.appstack.R
import com.impvhc.appstack.common.BaseActivity
import com.impvhc.stack.annotation.BindViewModel
import com.impvhc.stack.extension.withViewModel
import com.impvhc.stack.type.UiModelVM
import kotlinx.android.synthetic.main.activity_detail2.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @BindViewModel
    lateinit var viewModel: DetailViewModel

    override fun processUiModel(uiModelVM: UiModelVM) {
        when (uiModelVM) {
            is DetailState -> {
                textView3.text = uiModelVM.name
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)
        viewModel = withViewModel(factory) {}
    }
}
