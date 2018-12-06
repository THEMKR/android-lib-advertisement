package com.lory.library.advertisement.task

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.gson.Gson
import com.lory.library.advertisement.dto.DTOAppConfig
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Utils
import com.lory.library.firebaselib.BaseFirebaseTask
import com.lory.library.firebaselib.FirebaseCallBack

internal class FirebaseFetchAppConfigTask : BaseFirebaseTask<DTOAppConfig> {

    /**
     * Constructor
     * @param context
     * @param asyncCallBack
     */
    constructor(context: Context, asyncCallBack: FirebaseCallBack<DTOAppConfig>) : super(context, asyncCallBack) {
        // TO-DO
    }

    override fun getFirebaseDatabaseURL(): String? {
        return PrefData.getString(context, PrefData.Key.FIREBASE_DATABASE_URL)
    }

    override fun isBulkRequest(): Boolean {
        return false
    }

    override fun getFirebaseDatabasePathList(): ArrayList<ArrayList<String>> {
        val path = arrayListOf(Constants.FIREBASE_KEYS.APP_LIST, Utils.getAppFirebaseKey(context))
        return arrayListOf(path)
    }

    override fun parseFirebaseDataSnapShot(dataSnapshot: DataSnapshot): DTOAppConfig {
        try {
            val gson = Gson()
            val json = gson.toJson(dataSnapshot.value)
            val dto = gson.fromJson<DTOAppConfig>(json, DTOAppConfig::class.java)
            dto.isSuccess = true
            return dto
        } catch (e: Exception) {
            Log.e("MKR","FirebaseFetchAppConfigTask.parseFirebaseDataSnapShot()  ${e.message}     ${dataSnapshot}")
            val dto = DTOAppConfig()
            dto.isSuccess = false
            dto.errorCode = ERROR.MISCELLANEOUS_ERROR_CODE
            dto.errorMessage = "${ERROR.MISCELLANEOUS_ERROR_MESSAGE} ${e.message}"
            return dto
        }
    }

    override fun parseFirebaseDataSnapShot(dataSnapshot: ArrayList<Any>): DTOAppConfig {
        val dto = DTOAppConfig()
        dto.isSuccess = false
        dto.errorCode = ERROR.MISCELLANEOUS_ERROR_CODE
        dto.errorMessage = "${ERROR.MISCELLANEOUS_ERROR_MESSAGE} Unknown method call"
        return dto
    }
}