package com.dashrath.kotlinrepositorypattern.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dashrath.kotlinrepositorypattern.BR


/**
 * Used for handle common methods of activities
 */
abstract class BaseActivity<VM : BaseVM, VDB : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: VDB
    abstract val layout: Int
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Binding and set view model variable of activity.
         * set lifecycle owner.
         */
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

}