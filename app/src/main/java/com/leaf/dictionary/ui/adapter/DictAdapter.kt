package com.leaf.dictionary.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leaf.dictionary.R
import com.leaf.dictionary.extensions.inflate
import com.leaf.dictionary.data.models.Dictionary
import com.leaf.dictionary.data.models.DictionaryType

class DictAdapter(private val list: List<String>) :
    RecyclerView.Adapter<DictViewHolder>() {

    private var onClickListener: ((String) -> Unit)? = null

    fun setOnClickListener(clickListener: (String) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictViewHolder =
        DictViewHolder.create(parent, onClickListener)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DictViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

class DictViewHolder(view: View, private val clickListener: ((String) -> Unit)?) : RecyclerView.ViewHolder(view) {
    private val layout : FrameLayout = view.findViewById(R.id.item_layout)
    private val word: TextView = view.findViewById(R.id.text_word)

    fun bind(word: String) {
        this.word.text = word
        layout.setOnClickListener {
            clickListener?.invoke(word)
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup, clickListener: ((String) -> Unit)?): DictViewHolder =
            DictViewHolder(viewGroup.inflate(R.layout.dict_item), clickListener)
    }

}
