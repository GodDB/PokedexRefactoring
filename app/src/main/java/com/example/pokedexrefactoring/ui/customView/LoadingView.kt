package com.example.pokedexrefactoring.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.pokedexrefactoring.databinding.ViewLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    }

}