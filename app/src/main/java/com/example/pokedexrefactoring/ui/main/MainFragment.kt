package com.example.pokedexrefactoring.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pokedexrefactoring.R
import com.example.pokedexrefactoring.data.remote.model.Pokemon
import com.example.pokedexrefactoring.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance(onItemClick: (View, Pokemon) -> Unit): MainFragment {
            return MainFragment().apply {
                onItemClickListener = onItemClick
            }
        }
    }

    private val viewModel by viewModels<MainViewModel>()
    private var onItemClickListener: ((View, Pokemon) -> Unit)? = null
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            adapter = PokemonAdapter(viewModel)
        }

        startObserve()
    }

    private fun startObserve() {
        viewModel.showPokemonDetail.observe(viewLifecycleOwner, Observer {
            it.runOnEventIfNotHandled {
                onItemClickListener?.invoke(
                    binding.rvPokemonList.layoutManager!!.findViewByPosition(it.first)!!.findViewById(R.id.cardView), it.second
                )
            }
        })

    }

}