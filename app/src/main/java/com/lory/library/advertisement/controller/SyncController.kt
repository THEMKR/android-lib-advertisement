package com.lory.library.advertisement.controller

import android.app.Activity
import android.app.Application
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.dto.DTOAppConfig
import com.lory.library.advertisement.task.AsyncTaskProvider
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.mkrworld.androidlibasynctask.AsyncCallBack

class SyncController {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".SyncController"
    }

    private val activity: Activity
    private val applicationContext: Application
    private val asyncTaskProvider: AsyncTaskProvider
    private val asyncCallBackAppConfig = object : AsyncCallBack<DTOAppConfig, Any> {
        override fun onProgress(progress: Any?) {
            Tracer.debug(TAG, "onProgress: ")
        }

        override fun onSuccess(mkr: DTOAppConfig?) {
            Tracer.debug(TAG, "onSuccess: ")
        }
    }

    /**
     * Constructor
     * @param activity
     */
    constructor(activity: Activity) {
        Tracer.debug(TAG, "Constructor : ")
        this.activity = activity
        applicationContext = activity.application
        asyncTaskProvider = AsyncTaskProvider()
        asyncTaskProvider.attachProvider()
    }

    /**
     * Method to sync Library with the server
     */
    fun syncServer() {
        Tracer.debug(TAG, "syncServer: ")
        if ((PrefData.getLong(applicationContext, PrefData.Key.SYNC_TIME) + PrefData.getLong(applicationContext, PrefData.Key.SYNC_INTERVAL)) > System.currentTimeMillis()) {
            return
        }
        asyncTaskProvider.fetchAppConfig(applicationContext, asyncCallBackAppConfig)
    }
}