package com.example.todoapp.presentation.note

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.common.base.BaseFragment
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.databinding.FragmentCreateNoteBinding
import com.example.todoapp.presentation.home.HomeUiState
import com.example.todoapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment : BaseFragment<FragmentCreateNoteBinding,HomeViewModel>(FragmentCreateNoteBinding::inflate) {
    override val viewmodel: HomeViewModel
        by viewModels()

    override fun onViewCreateFinished() {
        setup()

    }

    override fun observeData() {
        viewmodel.state.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.CheckError->{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
                is HomeUiState.SuccessSave->{
                    findNavController().popBackStack()
                }
                else->Unit
            }
        }

    }
    private fun setup(){
        binding.ButtonBack.setOnClickListener { findNavController().popBackStack() }
        binding.ButtonSave.setOnClickListener {
            val title =binding.titleEditText.text.toString()
            val desc =binding.descriptionEditText.text.toString()
            viewmodel.saveNote(title, desc)
        }
    }


}