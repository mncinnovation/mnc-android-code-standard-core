package com.mncgroup.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * User Model
 * @property id id user
 * @property token token auth user
 * @property name name of user
 * @property email email user
 * @property pictureUrl photo of user
 */
// TODO : Update this user model class data following business model project
@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var token: String? = null,
    var name: String,
    var email: String,
    var pictureUrl: String
) : Serializable {

    fun isLoggedIn(): Boolean {
        return !token.isNullOrEmpty()
    }
}