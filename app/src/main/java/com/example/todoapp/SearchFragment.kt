package com.example.todoapp

import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.common.base.BaseAdapter
import com.example.todoapp.common.base.BaseFragment
import com.example.todoapp.common.extensions.gone
import com.example.todoapp.common.extensions.visible
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.data.mapper.toNoteEntity
import com.example.todoapp.databinding.FragmentSearchBinding
import com.example.todoapp.databinding.ItemNoteBinding
import com.example.todoapp.domain.model.NoteUiModel
import com.example.todoapp.presentation.home.HomeUiState
import com.example.todoapp.presentation.home.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding,HomeViewModel>(FragmentSearchBinding::inflate) {

    override val viewmodel: HomeViewModel by viewModels()

    private val searchAdapter by lazy {
        BaseAdapter<NoteUiModel, ItemNoteBinding>(ItemNoteBinding::inflate) { itemNoteBinding, i ->
            notes = itemNoteBinding
        }
    }
    var jobs:Job?=null
    override fun onViewCreateFinished() {
        setRv()
        searchNote()
    }

    private fun setRv() {
        binding.noteRv.adapter=searchAdapter
    }

    private fun searchNote() {
        with(binding){
                searchIE.addTextChangedListener {
                    val search=it?.toString()
                    jobs?.cancel()
                    if (!search.isNullOrEmpty()) {
                        jobs=lifecycleScope.launch {
                            delay(400)
                            viewmodel.searchNote(search)
                        }
                    }else{
                       viewmodel.getAllData()
                    }

            }
        }
    }

    override fun observeData() {
        viewmodel.state.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.SearchSuccess->{
                    searchAdapter.submitList(it.list)
                    with(binding){
                        if (searchAdapter.itemCount<=0){
                            emptyLayout.visible()
                            noteRv.gone()
                        }else{
                            noteRv.visible()
                            emptyLayout.gone()

                        }
                    }
                }
                is HomeUiState.Failure->{
                    FancyToast.makeText(requireContext(),it.message,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                }
                else->Unit
            }
        }
    }

}