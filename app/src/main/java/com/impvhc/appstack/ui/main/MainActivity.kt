package com.impvhc.appstack.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.impvhc.appstack.R
import com.impvhc.appstack.common.BaseActivity
import com.impvhc.appstack.ui.detail.DetailActivity
import com.impvhc.stack.util.goTo
import com.impvhc.stack.util.withViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.info
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = withViewModel(factory) {
            info {
                "Welcome"
            }
        }
        button.setOnClickListener {
            viewModel.num = viewModel.num + 1
            goTo<DetailActivity>("TEST" to viewModel.num)
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
