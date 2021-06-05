package com.example.pokedexrefactoring.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPagination(
    recyclerView: RecyclerView,
    private val isLoading: () -> Boolean,
    private val loadMore: (Int) -> Unit,
    private val onLast: () -> Boolean = { true }
) : RecyclerView.OnScrollListener() {

    var threshold = 6
    var currentPage = 0

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        recyclerView.layoutManager?.let {
            val totalItemCount = it.itemCount
            val lastVisibleItemPosition = when (it) {
                is LinearLayoutManager -> it.findLastVisibleItemPosition()
                is GridLayoutManager -> it.findLastVisibleItemPosition()
                else -> return
            }

            if (onLast() || isLoading()) return

            if (lastVisibleItemPosition + threshold >= totalItemCount) {
                loadMore(++currentPage)
            }
        }
    }

}