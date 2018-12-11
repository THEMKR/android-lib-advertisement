package com.lory.library.advertisement.utils

class Constants {
    companion object {
        const val THREAD_SLEEP_TIME = 500L
        const val TAG = "AD_LIB"
    }

    interface FIREBASE_KEYS {
        companion object {
            const val APP_LIST = "appList"
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
            const val ADMOB_TEST_ID = "admob_test_device_id"
        }
    }
}