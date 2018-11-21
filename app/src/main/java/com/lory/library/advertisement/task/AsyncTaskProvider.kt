package com.lory.library.advertisement.task

import android.content.Context
import com.lory.library.advertisement.dto.DTOAppConfig
import com.mkrworld.androidlibasynctask.AsyncCallBack
import com.mkrworld.androidlibasynctask.BaseAsyncTaskProvider

class AsyncTaskProvider : BaseAsyncTaskProvider() {

    /**
     * Method to Fetch the App Config
     * @param context
     * @param asyncCallBack
     */
    fun fetchAppConfig(context: Context, asyncCallBack: AsyncCallBack<DTOAppConfig, Any>) {
        var task = FirebaseFetchConfigTask(context, object : AsyncCallBack<DTOAppConfig, Any> {

            override fun onProgress(progress: Any?) {
                notifyTaskProgress(asyncCallBack as AsyncCallBack<Any, Any>, progress!!)
            }

            override fun onSuccess(mkr: DTOAppConfig?) {
                notifyTaskResponse(asyncCallBack as AsyncCallBack<Any, Any>, mkr!!)
            }
        })
        task.executeTask()
    }
}