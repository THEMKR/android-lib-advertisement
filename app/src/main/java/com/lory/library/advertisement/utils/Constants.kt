package com.lory.library.advertisement.utils

class Constants {
    companion object {
        const val THREAD_SLEEP_TIME = 500L
    }

    interface FIREBASE_KEYS {
        companion object {
            const val APP_LIST = "appList"
            const val DEFAULT_CONFIG = "default"
        }
    }

    interface ERROR {
        companion object {
            const val WRONG_CALLING_CODE = 0
            const val WRONG_CALLING_MESSAGE = "Wrong calling"

            const val NO_NETWORK_CODE = 1
            const val NO_NETWORK_MESSAGE = "No Network"

            const val NOTHING_RECEIVED_CODE = 2
            const val NOTHING_RECEIVED_MESSAGE = "Nothing received from the server"
        }
    }

    /**
     * Class to hold the MetaData keys
     */
    interface ExceptionMessage {
        companion object {
            val LIB_NOT_INITIALIZED = "Advertisement Library is not initialized. Plz initialized it before any operation"
            val DOES_NOT_HAVE_REQUIRED_PERMISSION = "Plz give all the required permission before initialized this library"
            val BANNER_AD_NOT_SUPPORTED = "Banner Ad is not supported by Provider : "
            val INTERSTITIAL_AD_NOT_SUPPORTED = "Interstitial Ad is not supported by Provider : "
        }
    }

    /**
     * Class to hold the MetaData keys
     */
    class MetaDataKeys {
        companion object {
            const val DEFAULT_AD_CONFIG = "default_ad_config"
        }
    }
}