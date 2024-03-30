package com.example.todoapp.common.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseAdapter<Data:Any,ItemViewBinding:ViewBinding>(
    private val inflater: Inflater<ItemViewBinding>,
    private val onBind:ItemViewBinding.(Data,Int)->Unit
):ListAdapter<Data,BaseAdapter.GeneralViewHolder<ItemViewBinding>>(GeneralItemCallback<Data>()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneralViewHolder<ItemViewBinding> {
        return GeneralViewHolder(
            inflater(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GeneralViewHolder<ItemViewBinding>, position: Int) {
       holder.bind {
           onBind(getItem(position),position)
       }
    }


    class GeneralViewHolder<Binding:ViewBinding>(
        private val binding:Binding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(action: Binding.()->Unit){

            binding.action()
        }
    }
    class GeneralItemCallback<Data:Any>: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.hashCode()==newItem.hashCode()
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem==newItem
        }

    }
}