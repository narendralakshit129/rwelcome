package com.sagar.rwelocme.di

/*sealed class NetworkResult<T> {

    data class Success<T>(val data: T) : NetworkResult<T>()

    data class Error<T>(val message: String) : NetworkResult<T>()

    class Loading<T> : NetworkResult<T>()

}*/


sealed class NetworkResult<out T> {

    object Idle : NetworkResult<Nothing>()

    object Loading : NetworkResult<Nothing>()

    data class Success<out T>(val data: T) : NetworkResult<T>()

    data class Error(val message: String) : NetworkResult<Nothing>()
}