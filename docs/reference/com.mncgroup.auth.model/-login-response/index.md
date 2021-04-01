[reference](../../index.md) / [com.mncgroup.auth.model](../index.md) / [LoginResponse](./index.md)

# LoginResponse

`class LoginResponse : `[`BaseResponseModel`](../../com.mncgroup.common.model/-base-response-model/index.md)

An login response model

TODO: Update this following login contract API project.

Response API following.
{
    responseCode : "00",
    responseMessage : "Success",
    user : {
      id : 32,
      token : AHJ743HJD832BBJKFD8434JKFD894RH3UF8EFH4U7EHSFN8EW3Q93I30248392,
      name : "Bayu Wijaya",
      email : "bayu@mail.com",
      pictureUrl : "api.com/userimage.jpg"
    }
}

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoginResponse(responseCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, responseMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`)`<br>An login response model |

### Properties

| Name | Summary |
|---|---|
| [responseCode](response-code.md) | `val responseCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [responseMessage](response-message.md) | `val responseMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [user](user.md) | `val user: `[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md) |
