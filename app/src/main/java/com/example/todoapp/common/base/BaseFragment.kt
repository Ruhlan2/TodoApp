package com.example.todoapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

typealias Inflater<T> =(LayoutInflater,ViewGroup?,Boolean)->T
abstract class BaseFragment<VB:ViewBinding,VM:ViewModel>(
    private val inflater:Inflater<VB>
):Fragment() {

    private var _binding:VB?=null
     val binding get() = requireNotNull(_binding){
        "Binding is null"
    }

    protected abstract val viewmodel:VM
    protected abstract fun onViewCreateFinished()
    protected abstract fun observeData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=inflater(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreateFinished()
        observeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}