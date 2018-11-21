package com.lory.library.advertisement.task

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.network.ConnectivityInfoUtils
import com.mkrworld.androidlibasynctask.AsyncCallBack
import com.mkrworld.androidlibasynctask.BaseAsyncTask

internal abstract class BaseFirebaseTask<MKR, PROGRESS> : BaseAsyncTask<MKR, PROGRESS> {

    companion object {
        private val TAG: String = BuildConfig.BASE_TAG + ".BaseFirebaseTask";
    }

    private var lock: Boolean = false

    /**
     * Constructor
     * @param context
     * @param asyncCallBack
     */
    constructor(context: Context, asyncCallBack: AsyncCallBack<MKR, PROGRESS>) : super(context, asyncCallBack) {
        Tracer.debug(TAG, "Constructor: ");
    }

    override fun doInBackground(): MKR {
        Tracer.debug(TAG, "doInBackground: ")
        if (!ConnectivityInfoUtils.isConnected(getContext(), true)) {
            return getNoNetworkError()
        }
        var firebaseData: FirebaseData
        var retryCount = 0
        while (true) {
            retryCount++
            firebaseData = executeFirebase()
            Tracer.debug(TAG, "doInBackground: $retryCount   $firebaseData")
            if (retryCount >= getMaxRetryCount()) {
                break
            }
            if ((firebaseData.isSingleData && firebaseData.data is DataSnapshot)) {
                break
            }
            if (!firebaseData.isSingleData && isFirebaseDataSuccess(firebaseData.dataArray)) {
                break
            }
        }

        if (firebaseData != null) {
            if (firebaseData.isSingleData) {
                return if (firebaseData.data is DataSnapshot) {
                    parseFirebaseDataSnapShot(firebaseData.data as DataSnapshot)
                } else if (firebaseData.data is DatabaseError) {
                    parseFirebaseDataError(firebaseData.data as DatabaseError)
                } else {
                    parseFirebaseDataReference()
                }
            } else {
                return parseFirebaseDataSnapShot(firebaseData.dataArray)
            }
        } else {
            return getNoResponseReceivedError()
        }
    }

    /**
     * Method called in back Thread. Method to execute Firebase Api and get the Data from FireBase.
     * Overrider handel the Firebase call
     * @return DataSnapshot/DatabaseError
     */
    protected abstract fun executeFirebase(): FirebaseData

    /**
     * Method to get the Data from Firebase
     * @param dataSnapshot
     */
    protected abstract fun parseFirebaseDataSnapShot(dataSnapshot: DataSnapshot): MKR

    /**
     * Method to get the Data from Firebase Ref
     * @param databaseReference
     */
    abstract fun parseFirebaseDataReference(): MKR

    /**
     * Method to get the Data from Firebase
     * @param dataSnapshot
     */
    protected abstract fun parseFirebaseDataSnapShot(dataSnapshotList: ArrayList<Any>): MKR

    /**
     * Method to get the Data from Firebase
     * @param databaseError Array<DataSnapshot/DatabaseError>
     */
    protected abstract fun parseFirebaseDataError(databaseError: DatabaseError): MKR

    /**
     * Method to get the Response when Network is not there
     */
    protected abstract fun getNoNetworkError(): MKR

    /**
     * Method to get the Response when No response received from firebase
     */
    protected abstract fun getNoResponseReceivedError(): MKR

    /**
     * Method to get the Max retry count
     */
    protected fun getMaxRetryCount(): Int {
        return 5
    }

    /**
     * Method to unlock the task
     */
    @Synchronized
    protected fun unlockTask() {
        Tracer.debug(TAG, "unlockTask: ")
        lock = false
    }

    /**
     * Method to lock the task
     */
    @Synchronized
    protected fun lockTask() {
        Tracer.debug(TAG, "lockTask: ")
        lock = true
    }

    /**
     * Method to check weather the task is locked or not
     */
    @Synchronized
    protected fun isTaskLock(): Boolean {
        return lock
    }

    /**
     * Method to check weather the List is Success or Not
     * @param dataArray
     */
    private fun isFirebaseDataSuccess(dataArray: ArrayList<Any>): Boolean {
        for (data in dataArray) {
            if (data is DataSnapshot) {
                return true
            }
        }
        return false
    }

    /**
     * Class to hold the firebsae data
     */
    class FirebaseData {
        lateinit var data: Any
        lateinit var dataArray: ArrayList<Any>
        var isSingleData: Boolean = true

        /**
         * Constructor
         * @param data
         */
        constructor(data: Any) {
            this.data = data
            isSingleData = true
        }

        /**
         * Constructor
         * @param dataArray
         */
        constructor(dataArray: ArrayList<Any>) {
            this.dataArray = dataArray
            isSingleData = false
        }

    }
}