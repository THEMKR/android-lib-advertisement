package com.lory.library.advertisement.task

import android.content.Context
import com.lory.library.advertisement.dto.DTOAppConfig
import com.lory.library.firebaselib.BaseFirebaseTask
import com.lory.library.firebaselib.BaseFirebaseTaskProvider
import com.lory.library.firebaselib.FirebaseCallBack

class AsyncTaskProvider : BaseFirebaseTaskProvider() {

    /**
     * Method to Fetch the App Config
     * @param context
     * @param asyncCallBack
     */
    fun fetchAppConfig(context: Context, asyncCallBack: FirebaseCallBack<DTOAppConfig>) {
        var task = FirebaseFetchAppConfigTask(context, object : FirebaseCallBack<DTOAppConfig> {
            override fun onFirebaseFailed(errorCode: Int, errorMessage: String) {
                notifyTaskFailed(asyncCallBack as FirebaseCallBack<Any>, errorCode, errorMessage)
            }

            override fun onFirebaseSuccess(mkr: DTOAppConfig?) {
                if (mkr != null) {
                    notifyTaskSuccess(asyncCallBack as FirebaseCallBack<Any>, mkr)
                } else {
                    notifyTaskFailed(asyncCallBack as FirebaseCallBack<Any>, BaseFirebaseTask.ERROR.NOTHING_RECEIVED_CODE, BaseFirebaseTask.ERROR.NOTHING_RECEIVED_MESSAGE)
                }
            }

        })
        task.executeTask()
    }
}