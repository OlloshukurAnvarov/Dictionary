package com.leaf.dictionary.ui.fragment.translatedFragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.leaf.dictionary.R
import com.leaf.dictionary.data.models.DictionaryType
import com.leaf.dictionary.databinding.FragmentTranslatedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TranslatedFragment : Fragment(R.layout.fragment_translated) {
    private val binding: FragmentTranslatedBinding by viewBinding()
    private val viewModel: TranslatedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val type = arguments?.getString("type-index") ?: "EN_UZ"
        val word = arguments?.getString("selected-item") ?: ""

        requireActivity().title = word

        viewModel.oppositeWord(DictionaryType.valueOf(type), word)

        viewModel.word.observe(viewLifecycleOwner) { oWord ->
            binding.textTranslatedWord.text = oWord
        }
    }
}