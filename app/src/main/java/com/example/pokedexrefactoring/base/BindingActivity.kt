package com.example.pokedexrefactoring.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.pokedexrefactoring.extensions.binding
import com.example.pokedexrefactoring.extensions.initBinding

abstract class BindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {
    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "AppCompatActivity $this binding cannot be accessed before onCreate() or after onDestroy()"
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = binding(layoutResId)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }
    protected inline fun initBinding(initBlock: T.() -> Unit) = binding.initBinding(initBlock)
}