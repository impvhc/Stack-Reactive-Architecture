package com.impvhc.appstack.ui.detail

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.impvhc.appstack.R
import com.impvhc.appstack.common.BaseActivity
import com.impvhc.stack.util.getViewModel
import javax.inject.Inject

class DetailActivity : BaseActivity() {


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = getViewModel(factory)
    }
}
