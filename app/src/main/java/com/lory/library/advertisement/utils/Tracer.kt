package com.lory.library.advertisement.utils

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast

/**
 * Created by delhivery on 21/3/16.
 */
class Tracer {
    companion object {
        var LOG_ENABLE = false

        /**
         * Method to print debug log<br></br>
         * [Log] Information
         *
         * @param TAG
         * @param message
         */
        fun debug(TAG: String, message: String) {
            if (LOG_ENABLE) {
                Log.d(TAG, message)
            }
        }

        /**
         * Method to print error log<br></br>
         * [Log] Error
         *
         * @param TAG
         * @param message
         */
        fun error(TAG: String, message: String) {
            if (LOG_ENABLE) {
                Log.e(TAG, message)
            }
        }

        /**
         * Method to print information log<br></br>
         * [Log] Debug
         *
         * @param TAG
         * @param message
         */
        fun info(TAG: String, message: String) {
            if (LOG_ENABLE) {
                Log.i(TAG, message)
            }
        }

        /**
         * Show TOAST<br></br>
         *
         * @param context activity context.
         * @param text    The text to show.  Can be formatted text.
         */
        fun showToast(context: Context, text: String) {
            val toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
