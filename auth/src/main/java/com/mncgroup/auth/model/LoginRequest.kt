package com.mncgroup.auth.model

/**
 * An login request data
 *
 * Request send data following
 * {
 *   email : "bayu@mail.com",
 *   password : "qwerty123"
 * }
 *
 * TODO: Update this login request data following login request API contract.
 */
class LoginRequest (
    var email : String,
    var password : String
)