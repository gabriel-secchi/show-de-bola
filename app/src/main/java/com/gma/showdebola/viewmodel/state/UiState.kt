package com.gma.showdebola.viewmodel.state

/**
 * A sealed hierarchy describing the state of the text generation.
 */
sealed class UiState<out T> {

    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
    data class Success<T>(val data: T?) : UiState<T>()

}