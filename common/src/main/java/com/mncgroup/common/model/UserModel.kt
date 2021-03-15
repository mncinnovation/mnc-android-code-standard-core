package com.mncgroup.common.model

/**
 * User Model
 * @property id id user
 * @property token token auth user
 * @property name name of user
 * @property email email user
 * @property pictureUrl photo of user
 */
// TODO : Update this user model class data following business model project
data class UserModel(
    var id: Int,
    var token : String,
    var name: String,
    var email: String,
    var pictureUrl: String
)