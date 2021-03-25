package com.mncgroup.common.network

/**
 * This is class constants value of response API.
 * [API_OK] is an success response API
 * [API_ERROR_DEVICE] is an error with device that used application may need handled to navigate to login page
 * [API_ERROR_LOGGED_OUT] is an constant response that inform front end to login before access the api, so the app may need handled it to navigate to login page
 * [API_ERROR_SESSION_EXPIRED] is an constant response that inform front end to relogin caused session user expired, so the app may need handled it to navigate to login page
 * [API_ERROR_NEED_UPDATE] is an constant response that inform front end to update the app, so the app may need handled it to inform user about the new version update
 * TODO: Please to update this constant value following API contract.
 */
class ConstantApi {
    companion object {
        const val API_OK = "00"
        const val API_ERROR_DEVICE = "42"
        const val API_ERROR_LOGGED_OUT = "46"
        const val API_ERROR_SESSION_EXPIRED = "48"
        const val API_ERROR_NEED_UPDATE = "90"
    }
}
