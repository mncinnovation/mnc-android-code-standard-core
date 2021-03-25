[reference](../../index.md) / [com.mncgroup.common.model](../index.md) / [UserModel](./index.md)

# UserModel

`data class UserModel : `[`Serializable`](https://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)

User Model

TODO: Update this user model class data following business model project

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UserModel(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, pictureUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>User Model |

### Properties

| Name | Summary |
|---|---|
| [email](email.md) | `var email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>email user |
| [id](id.md) | `var id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>id user |
| [name](name.md) | `var name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>name of user |
| [pictureUrl](picture-url.md) | `var pictureUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>photo of user |
| [token](token.md) | `var token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>token auth user |

### Functions

| Name | Summary |
|---|---|
| [isLoggedIn](is-logged-in.md) | `fun isLoggedIn(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
