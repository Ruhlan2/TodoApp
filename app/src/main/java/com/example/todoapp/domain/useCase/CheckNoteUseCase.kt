package com.example.todoapp.domain.useCase

import com.example.todoapp.R
import com.example.todoapp.common.utils.CheckResult

class CheckNoteUseCase {

    fun executeFields(
        title:String,
        desc:String
    ):CheckResult{
        if (title.isEmpty()||desc.isEmpty()){
            return CheckResult(
                success = false,
                errorMessage = R.string.error_message
            )
        }
        return CheckResult(
            success = true
        )
    }
}