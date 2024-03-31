package com.example.todoapp.common.utils

import androidx.annotation.StringRes

data class CheckResult (
    val success :Boolean,
    @StringRes val errorMessage:Int?=null
)