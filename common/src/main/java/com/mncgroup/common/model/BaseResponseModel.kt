package com.mncgroup.common.model

/**
 * Base response
 * This is following API response like this.
 * {
 *    responseCode : "00",
 *    responseMessage : "Success",
 *    ... other responses
 * }
 *
 * TODO : Update base response following API contract project.
 */

abstract class BaseResponseModel {
    abstract val responseCode: String
    abstract val responseMessage: String
}


