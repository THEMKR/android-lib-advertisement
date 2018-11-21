package com.lory.library.advertisement.task

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.dto.DTOAppConfig
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.mkrworld.androidlibasynctask.AsyncCallBack

internal class FirebaseFetchConfigTask : BaseFirebaseTask<DTOAppConfig, Any> {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".FirebaseFetchConfigTask"
    }

    /**
     * Constructor
     * @param context
     * @param asyncCallBack
     */
    constructor(context: Context, asyncCallBack: AsyncCallBack<DTOAppConfig, Any>) : super(context, asyncCallBack) {
        // TO-DO
    }

    override fun executeFirebase(): FirebaseData {
        Tracer.debug(TAG, "executeFirebase: ")
        var firebaseData: Any? = null
        lockTask()
        val databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CONSTANT.APP_CONFIG)
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                firebaseData = dataSnapshot
                unlockTask()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                firebaseData = databaseError
                unlockTask()
            }
        })

        // LOOP LOCK
        while (isTaskLock()) {
            Thread.sleep(Constants.THREAD_SLEEP_TIME)
        }
        return FirebaseData(firebaseData!!)
    }

    override fun parseFirebaseDataSnapShot(dataSnapshot: DataSnapshot): DTOAppConfig {
        Tracer.debug(TAG, "parseFirebaseDataSnapShot: ")
        val dto = DTOAppConfig()
        val dataMap: HashMap<String, Any>? = dataSnapshot!!.value as HashMap<String, Any>
        try {
            if (dataMap != null) {
                dto.isSuccess = true
                dto.appVersion = dataMap.get(Constants.FIREBASE_CONSTANT.APP_VER) as Long
            }
        } catch (e: Exception) {
            Tracer.debug(TAG, "parseFirebaseDataSnapShot: ${e.message}")
        }
        return dto
    }

    override fun parseFirebaseDataSnapShot(dataSnapshot: ArrayList<Any>): DTOAppConfig {
        val dto = DTOAppConfig()
        dto.isSuccess = false
        dto.errorMessage = Constants.ERROR.WRONG_CALLING_MESSAGE
        dto.errorCode = Constants.ERROR.NO_NETWORK_CODE
        return dto
    }

    override fun parseFirebaseDataError(databaseError: DatabaseError): DTOAppConfig {
        Tracer.debug(TAG, "parseFirebaseDataError: ")
        val dto = DTOAppConfig()
        dto.isSuccess = false
        dto.errorMessage = databaseError.message
        dto.errorCode = databaseError.code
        return dto
    }

    override fun getNoNetworkError(): DTOAppConfig {
        Tracer.debug(TAG, "getNoNetworkError: ")
        val dto = DTOAppConfig()
        dto.isSuccess = false
        dto.errorMessage = Constants.ERROR.NO_NETWORK_MESSAGE
        dto.errorCode = Constants.ERROR.NO_NETWORK_CODE
        return dto
    }

    override fun getNoResponseReceivedError(): DTOAppConfig {
        Tracer.debug(TAG, "getNoResponseReceivedError: ")
        val dto = DTOAppConfig()
        dto.isSuccess = false
        dto.errorMessage = Constants.ERROR.NOTHING_RECEIVED_MESSAGE
        dto.errorCode = Constants.ERROR.NOTHING_RECEIVED_CODE
        return dto
    }

    override fun parseFirebaseDataReference(): DTOAppConfig {
        Tracer.debug(TAG, "parseFirebaseDataReference: ")
        val dto = DTOAppConfig()
        dto.isSuccess = false
        dto.errorMessage = Constants.ERROR.WRONG_CALLING_MESSAGE
        dto.errorCode = Constants.ERROR.NO_NETWORK_CODE
        return dto
    }
}