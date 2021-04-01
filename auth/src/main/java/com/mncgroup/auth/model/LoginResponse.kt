package com.mncgroup.auth.model

import com.mncgroup.common.model.BaseResponseModel
import com.mncgroup.common.model.UserModel

/**
 * An login response model
 *
 * TODO: Update this following login contract API project.
 *
 * Response API following.
 * {
 *    responseCode : "00",
 *    responseMessage : "Success",
 *    user : {
 *      id : 32,
 *      token : AHJ743HJD832BBJKFD8434JKFD894RH3UF8EFH4U7EHSFN8EW3Q93I30248392,
 *      name : "Bayu Wijaya",
 *      email : "bayu@mail.com",
 *      pictureUrl : "api.com/userimage.jpg"
 *    }
 * }
 *
 */
class LoginResponse (
    override val responseCode: String,
    override val responseMessage: String,
    val user : UserModel
) : BaseResponseModel()