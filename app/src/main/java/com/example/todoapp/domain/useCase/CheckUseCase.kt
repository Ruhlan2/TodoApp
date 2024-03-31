package com.example.todoapp.domain.useCase

import com.example.todoapp.R
import com.example.todoapp.common.utils.CheckResult

class CheckUseCase {

    fun executeTitle(title:String):CheckResult{
        if (title.isEmpty()){
            CheckResult(
                success = false,
                errorMessage = R.string.error_message_1
            )
        }

        return CheckResult(success = true)
    }

    fun executeDescription(desc:String):CheckResult{
        if (desc.isEmpty()){
            CheckResult(
                success = false,
                errorMessage = R.string.error_message_2
            )
        }
        return CheckResult(success = true)
    }
}