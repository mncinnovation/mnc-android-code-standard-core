[reference](../../index.md) / [com.mncgroup.common.model](../index.md) / [BaseResponseModel](./index.md)

# BaseResponseModel

`abstract class BaseResponseModel`

Base response
This is following API response like this.
{
    "responseCode" : "00",
    "responseMessage" : "Success",
    ... other responses
}

TODO: Update [BaseResponseModel](./index.md) following API contract project.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseResponseModel()`<br>Base response This is following API response like this. {     "responseCode" : "00",     "responseMessage" : "Success",     ... other responses } |

### Properties

| Name | Summary |
|---|---|
| [responseCode](response-code.md) | `abstract val responseCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [responseMessage](response-message.md) | `abstract val responseMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [LoginResponse](../../com.mncgroup.auth.model/-login-response/index.md) | `class LoginResponse : `[`BaseResponseModel`](./index.md)<br>An login response model |
