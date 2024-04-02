package com.example.todoapp.common.extensions

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.todoapp.databinding.LayoutLoadingBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.w3c.dom.Text


fun View.visible(){
    this.visibility=View.VISIBLE
}
fun View.invisible(){
    this.visibility=View.INVISIBLE
}
fun View.gone(){
    this.visibility=View.GONE
}
fun Context.createProgressDialog():Dialog{

    val dialog=Dialog(this)
    val binding=LayoutLoadingBinding.inflate(LayoutInflater.from(this))

    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.setCancelable(false)
    dialog.setContentView(binding.root)

    return dialog
}

fun Context.showShortInfoMessage(message:String){
    FancyToast.makeText(this,message,FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show()
}