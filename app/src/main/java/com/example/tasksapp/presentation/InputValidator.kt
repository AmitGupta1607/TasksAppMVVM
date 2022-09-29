package com.example.tasksapp.presentation

object InputValidator {


    fun validateInput(title: String, subtitle: String): Boolean {
        if (title.trim().isEmpty()) {
            return false
        }
        return true
    }
}