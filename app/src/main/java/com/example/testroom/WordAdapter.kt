package com.example.testroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testroom.databinding.ItemTextBinding

class WordAdapter(private val wordsListener: WordsListener) : RecyclerView.Adapter<WordAdapter.ItemViewHolder>() {

    private var words: List<WordEntity> = emptyList()

    fun setWords(wordEntity: List<WordEntity>){
        words = wordEntity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(words[position])

    }

    inner class ItemViewHolder(private val binding: ItemTextBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(wordEntity: WordEntity){
                val word : String = wordEntity.word
                binding.tvText.text = word
                binding.buttonDelete.setOnClickListener {
                    wordsListener.deleteWord(wordEntity)
                }
            }
    }
}