package dev.seabat.android.serverlessbbs.data

sealed class BbsThreadResult<T> {
    data class Loading<T>(val isLoading: Boolean) : BbsThreadResult<T>()
    data class Success<T>(val data: T) : BbsThreadResult<T>()
    data class Failure<T>(val errorMessage: String) : BbsThreadResult<T>()
}