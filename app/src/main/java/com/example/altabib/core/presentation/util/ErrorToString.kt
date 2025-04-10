package com.example.altabib.core.presentation.util

import android.content.Context
import com.example.altabib.R
import com.example.altabib.core.domain.util.DataError

/**
 * Extension function for [DataError] to get a user-friendly error message.
 *
 * This function maps a [DataError] to a corresponding string.
 *
 * @param context The [Context] used to retrieve the localized string resource.
 * @return A user-friendly error message string.
 */
fun DataError.getMessage(context: Context): String {

    return when (this) {
        is DataError.NetworkError.NoInternet -> context.getString(R.string.error_no_internet)
        is DataError.NetworkError.RequestTimeout -> context.getString(R.string.error_request_timeout)
        is DataError.NetworkError.Serialization -> context.getString(R.string.error_serialization)
        is DataError.NetworkError.ServerError -> context.getString(R.string.error_unknown)
        is DataError.NetworkError.TooManyRequests -> context.getString(R.string.error_too_many_requests)
        is DataError.NetworkError.Unknown -> context.getString(R.string.error_unknown)
        is DataError.LocalError -> context.getString(R.string.local_data_error)
        is DataError.NoSearchResult -> context.getString(R.string.no_results)
        is DataError.RetrievalError -> this.message
        is DataError.GeneralError -> context.getString(R.string.error_unknown)
    }
}
