package com.example.altabib.core.presentation.util

import android.content.Context
import com.example.altabib.R
import com.example.altabib.core.domain.util.DataError
import com.example.altabib.utils.LocaleHelper

/**
 * Extension function for [DataError] to get a user-friendly error message.
 *
 * This function maps a [DataError] to a corresponding string.
 *
 * @param context The [Context] used to retrieve the localized string resource.
 * @return A user-friendly error message string.
 */
fun DataError.getMessage(context: Context): String {
    val mContext = LocaleHelper.setLocale(context, LocaleHelper.getCurrentLanguageEnum(context))
    return when (this) {
        is DataError.NetworkError.NoInternet -> mContext.getString(R.string.error_no_internet)
        is DataError.NetworkError.RequestTimeout -> mContext.getString(R.string.error_request_timeout)
        is DataError.NetworkError.Serialization -> mContext.getString(R.string.error_serialization)
        is DataError.NetworkError.ServerError -> mContext.getString(R.string.error_unknown)
        is DataError.NetworkError.TooManyRequests -> mContext.getString(R.string.error_too_many_requests)
        is DataError.NetworkError.Unknown -> mContext.getString(R.string.error_unknown)
        is DataError.LocalError -> mContext.getString(R.string.local_data_error)
        is DataError.NoSearchResult -> mContext.getString(R.string.no_results)
        is DataError.GeneralError -> mContext.getString(R.string.error_unknown)
        is DataError.FailedToRetrieveData -> mContext.getString(R.string.error_failed_to_retrieve_data)
        is DataError.FailedToUpdateData -> mContext.getString(R.string.error_failed_to_update_data)
    }
}
