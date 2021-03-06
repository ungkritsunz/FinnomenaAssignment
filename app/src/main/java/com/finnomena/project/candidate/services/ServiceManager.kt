package com.finnomena.project.candidate.services

import android.util.Log
import com.finnomena.project.candidate.Application
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.utils.Constants
import com.finnomena.project.candidate.utils.UtilsUI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ServiceManager {

    private const val TAG = "ServiceManager"

    fun <T> service(
        call: Call<T>,
        callback: (result: T?) -> Unit
    ) {
        // check internet
        if (!UtilsUI.isNetworkAvailable()) {
            UtilsUI.messageDialogUtil(
                null,
                Application.mApplicationContext.getString(R.string.utils_nocontent_network)
            )
        } else {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.code() == Constants.HTTP_OK) {
                        callback(response.body())
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.e(TAG, "onFailure", t)
                    if (Constants.SOCKET_TIMEOUT == t.toString().substring(
                            0,
                            t.toString().indexOf(":")
                        )
                    ) {
                        UtilsUI.messageDialogUtil(
                            null,
                            Application.mApplicationContext.getString(R.string.utils_nocontent_ro_time)
                        )
                    } else {
                        UtilsUI.messageDialogUtil(
                            null,
                            Application.mApplicationContext.getString(R.string.utils_nocontent)
                        )
                    }
                }
            })
        }
    }
}
