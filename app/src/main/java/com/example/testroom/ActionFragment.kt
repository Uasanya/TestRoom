package com.example.testroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.testroom.databinding.FragmentActionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActionFragment : Fragment(), WordsListener {

    private var _binding: FragmentActionBinding? = null
    private val binding get() = _binding!!
    private val wordAdapter: WordAdapter = WordAdapter(this)
    private var db : MainDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDB.getDb(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAdd.setOnClickListener {
            val word = WordEntity(null, binding.etInput.text.toString())
            CoroutineScope(Dispatchers.IO).launch {
                db?.getDao()?.insertWord(word)
            }
        }
        db?.getDao()?.getWords()?.asLiveData()?.observe(viewLifecycleOwner) {
            wordAdapter.setWords(it)
            binding.rv.adapter = wordAdapter
            }
    }

    override fun deleteWord(wordEntity: WordEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            db?.getDao()?.deleteWord(wordEntity)
        }
    }
}