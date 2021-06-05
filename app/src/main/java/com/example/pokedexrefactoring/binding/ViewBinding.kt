package com.example.pokedexrefactoring.binding

import android.view.View
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.databinding.BindingAdapter

object ViewBinding {

    @JvmStatic
    @BindingAdapter("onBackPressed")
    fun bindBackPressed(v: View, isBackpressed: Boolean) {
        if (!isBackpressed) return

        val context = v.context
        if (context is OnBackPressedDispatcherOwner) {
            v.setOnClickListener {
                context.onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}