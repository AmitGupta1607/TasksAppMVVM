package com.example.tasksapp.common

sealed class Resource<T>(val data :T?=null) {
    class Success<T>(data: T):Resource<T>(data)
    class Error<Nothing>(val message:String?):Resource<Nothing>()
    class Loading<Nothing>(val isLoading:Boolean=false):Resource<Nothing>()
}