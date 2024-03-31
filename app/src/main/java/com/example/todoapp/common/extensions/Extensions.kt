package com.example.todoapp.common.extensions

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.example.todoapp.databinding.LayoutLoadingBinding
import kotlin.random.Random


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

fun CardView.setBackgroundColor(){
    val random= Random.Default
    val color= Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256))

    this.setCardBackgroundColor(color)
}