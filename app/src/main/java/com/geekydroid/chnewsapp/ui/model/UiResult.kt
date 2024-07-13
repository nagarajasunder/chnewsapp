package com.geekydroid.chnewsapp.ui.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiResult<T> {

    class Success<T>(val data: T) : UiResult<T>()
    class Error<T>(val message: UiText) : UiResult<T>()
    class Loading<T>(val message: UiText) : UiResult<T>()
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