package com.impvhc.appstack.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.impvhc.appstack.R
import com.impvhc.appstack.common.BaseActivity
import com.impvhc.appstack.ui.detail.DetailActivity
import com.impvhc.stack.annotation.BindViewModel
import com.impvhc.stack.extension.resetTo
import com.impvhc.stack.extension.withViewModel
import com.impvhc.stack.type.UiModelVM
import com.impvhc.stack.viewmodel.StackViewModel
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    /**
     * Provide a factory that knows how to create StackViewModel
     */
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    /**
     * Provide our StackModel - BindViewModel to bind with RX : multiples StackViewModel are supported
     */
    @BindViewModel
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Bind your viewModel with the activity*/
        viewModel = withViewModel(factory) {

            compositeDisposable.add(RxView.clicks(button)
                    .map {
                        button.isEnabled = false
                        progressBar.visibility = View.VISIBLE
                        Events.SaveName(editText.text.toString())
                    }
                    .subscribe { event: Events.SaveName? -> this.processUiEvent(event!!) })

        }
    }

    /**
     * Bind to {@link UiModel}.
     * @param uiModelVM - the [UiModelVM] from [StackViewModel] that backs the UI
     */
    override fun processUiModel(uiModelVM: UiModelVM) {
        when (uiModelVM) {
            is MainModel -> {
                textView.text = uiModelVM.name
                if (uiModelVM.login) {
                    Handler().postDelayed({
                        resetTo<DetailActivity>("login_name" to uiModelVM.name)
                    }, 1000)
                } else {
                    button.isEnabled = true
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

}
