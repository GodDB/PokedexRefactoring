@file:Suppress("UNCHECKED_CAST")

package com.example.pokedexrefactoring.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexrefactoring.BaseViewModel
import com.example.pokedexrefactoring.ui.main.MainViewModel
import com.example.pokedexrefactoring.util.RecyclerViewPagination
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(rv: RecyclerView, adapter: ListAdapter<*, *>) {
        rv.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("submitData")
    fun <T : Any> bindSubmitData(rv: RecyclerView, datas: List<T>?) {
        datas?.let {
            (rv.adapter as? ListAdapter<T, *>)?.submitList(datas)
        }
    }

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteCard")
    fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: MaterialCardView) {
        Glide.with(view.context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val rgb = palette?.dominantSwatch?.rgb
                        if (rgb != null) {
                            paletteCard.setCardBackgroundColor(rgb)
                        }
                    }.crossfade(true)
            ).into(view)
    }

    @JvmStatic
    @BindingAdapter("pagination")
    fun bindPagination( rv : RecyclerView, vm : BaseViewModel){
        RecyclerViewPagination(
            recyclerView = rv,
            isLoading = { vm.isLoading.value ?: false },
            loadMore = { (vm as? MainViewModel)?.fetchPokemonList() },
            onLast = { (vm as? MainViewModel)?.isPagingFinished ?: false }
        )
    }


}

