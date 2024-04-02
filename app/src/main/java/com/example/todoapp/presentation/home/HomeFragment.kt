package com.example.todoapp.presentation.home

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.todoapp.common.base.BaseAdapter
import com.example.todoapp.common.base.BaseFragment
import com.example.todoapp.common.extensions.createProgressDialog
import com.example.todoapp.common.extensions.gone
import com.example.todoapp.common.extensions.showShortInfoMessage
import com.example.todoapp.common.extensions.visible
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.data.mapper.toNoteEntity
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.databinding.ItemNoteBinding
import com.example.todoapp.domain.model.NoteUiModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(FragmentHomeBinding::inflate){
    override val viewmodel: HomeViewModel
        by viewModels()

    private val notesAdapter by lazy {
        BaseAdapter<NoteUiModel, ItemNoteBinding>(ItemNoteBinding::inflate) { itemNoteBinding, i ->
            notes = itemNoteBinding
            root.setOnClickListener {
                viewmodel.deleteAllNotes(itemNoteBinding.id)
            }
        }
    }
    override fun onViewCreateFinished() {
        setRv()
        setup()
        viewmodel.getAllData()
    }

    private fun setup() {
        with(binding){
            buttonAdd.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment())
            }
            ButtonSearch.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
        }
    }
    override fun observeData() {
        with(binding){
            val pd =requireActivity().createProgressDialog()
            viewmodel.state.observe(viewLifecycleOwner){
                when(it){
                    is HomeUiState.Failure->{
                        pd.cancel()
                        FancyToast.makeText(requireContext(),it.message,FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show()
                    }
                    is HomeUiState.Loading->{
                        pd.show()
                    }
                    is HomeUiState.Success->{
                        pd.cancel()
                        notesAdapter.submitList(it.list)
                        if (notesAdapter.itemCount<=0){
                            emptyLayout.visible()
                            notesRv.gone()

                        }
                        else {
                            emptyLayout.gone()
                            notesRv.visible()
                        }

                    }
                    is HomeUiState.DeleteSuccess->{
                        context?.showShortInfoMessage("Successfully deleted")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setRv(){
        binding.notesRv.adapter= notesAdapter
    }



}