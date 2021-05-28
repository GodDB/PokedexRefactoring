package com.example.pokedexrefactoring.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.pokedexrefactoring.base.BindingActivity


fun <T : ViewDataBinding> BindingActivity<T>.binding(@LayoutRes layoutId: Int): T {
    return DataBindingUtil.setContentView<T>(this, layoutId)
}

inline fun <T : ViewDataBinding> T.initBinding(initBlock: T.() -> Unit) =
    this.run(initBlock)

