package com.geekydroid.chnewsapp.ui.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    data class Loading<T>(val messageText: String? = null) : UiResult<T>(message = messageText)
    data class Success<T>(val successData: T) : UiResult<T>(data = successData)
    data class Error<T>(val errorMessage: String? = null) : UiResult<T>(message = errorMessage)

}

sealed class UiText {

    data class DynamicText(val text: String) : UiText()
    class StringResources(@StringRes val resId: Int, vararg val args: Any) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicText -> {
                this@UiText.text
            }

            is StringResources -> {
                stringResource(id = resId, *args)
            }
        }
    }
}