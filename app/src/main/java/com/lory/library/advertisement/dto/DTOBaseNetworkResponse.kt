package com.lory.library.advertisement.dto

open class DTOBaseNetworkResponse {

    /**
     * Check weather the result is Success or Not
     */
    var isSuccess: Boolean = false

    /**
     * Check weather the result is Success or Not
     */
    var errorCode: Int = -1

    /**
     * Check weather the result is Success or Not
     */
    var errorMessage: String = ""
        get() {
            return field.trim()
        }
}