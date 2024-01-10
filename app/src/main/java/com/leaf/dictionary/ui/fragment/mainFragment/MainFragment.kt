package com.leaf.dictionary.ui.fragment.mainFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.leaf.dictionary.R
import com.leaf.dictionary.data.models.DictionaryType
import com.leaf.dictionary.databinding.FragmentMainBinding
import com.leaf.dictionary.ui.adapter.DictAdapter
import com.leaf.dictionary.ui.fragment.translatedFragment.TranslatedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private var type = DictionaryType.EN_UZ
    private val data = ArrayList<String>()
    private val adapter by lazy { DictAdapter(data) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = getString(R.string.app_name)
        refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { data ->
            refresh()
            this.data.addAll(data)
        }

        adapter.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(
                    R.id.container, TranslatedFragment::class.java, bundleOf(
                        "type-index" to type.toString(),
                        "selected-item" to it
                    )
                )
                .addToBackStack("MainFragment")
                .commit()
        }
        viewModel.dictionaries(type)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dict_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val search = item.actionView as? SearchView
                search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText.isNullOrEmpty())
                            viewModel.dictionaries(type)
                        else {
                            viewModel.searchedDictionaries(type, newText)
                            refresh()
                        }
                        return true
                    }
                })
            }

            R.id.action_change -> {
                type = when (type) {
                    DictionaryType.EN_UZ -> DictionaryType.UZ_EN
                    DictionaryType.UZ_EN -> DictionaryType.EN_UZ
                }
                requireActivity().findViewById<View>(R.id.action_change)?.startAnimation(
                    AnimationUtils.loadAnimation(
                        requireContext(),
                        R.anim.circling_anim
                    )
                )
                viewModel.dictionaries(type)
                refresh()
            }
        }
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refresh() {
        data.clear()
        adapter.notifyDataSetChanged()
    }
}