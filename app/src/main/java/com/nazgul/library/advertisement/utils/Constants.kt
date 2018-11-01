package com.nazgul.library.advertisement.utils

class Constants {
    companion object {

    }

    /**
     * Class to hold the MetaData keys
     */
    interface ExceptionMessage {
        companion object {
            val LIB_NOT_INITIALIZED = "Advertisement Library is not initialized. Plz initialized it before any operation"
        }
    }

    /**
     * Class to hold the MetaData keys
     */
    class MetaDataKeys {
        companion object {
            const val AD_MOB_APP_ID = "com.google.android.gms.ads.APPLICATION_ID"
        }
    }
}