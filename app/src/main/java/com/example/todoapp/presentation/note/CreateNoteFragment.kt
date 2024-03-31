package com.example.todoapp.presentation.note

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.common.base.BaseFragment
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.databinding.FragmentCreateNoteBinding
import com.example.todoapp.presentation.home.HomeUiState
import com.example.todoapp.presentation.home.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast
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
                   FancyToast.makeText(requireContext(),resources.getString(it.message),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
               }
               else-> {
                   findNavController().popBackStack()
               }
           }
       }
    }
    private fun setup(){
        binding.ButtonBack.setOnClickListener { findNavController().popBackStack() }
        binding.ButtonSave.setOnClickListener {
            viewmodel.submitNote(binding.titleEditText.text.toString(),binding.descriptionEditText.text.toString())
        }
    }


}