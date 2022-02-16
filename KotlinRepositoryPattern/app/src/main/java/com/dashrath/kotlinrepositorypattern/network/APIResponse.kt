package com.dashrath.kotlinrepositorypattern.network

sealed class APIResponse<out T> {
    class Loading<out T>(val isProgressShow: Boolean) : APIResponse<T>()
    class Success<out T>(val data: T) : APIResponse<T>()
    class Error<out T>(val throwable: Throwable) : APIResponse<T>()
    object Remove : APIResponse<Nothing>()

}
