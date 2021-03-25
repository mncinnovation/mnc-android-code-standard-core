[reference](../../index.md) / [com.mncgroup.auth.ui.login](../index.md) / [LoginViewModel](./index.md)

# LoginViewModel

`class LoginViewModel : BaseViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoginViewModel(authRepository: `[`AuthRepository`](../../com.mncgroup.auth.repository/-auth-repository/index.md)`, userRepository: `[`UserRepository`](../../com.mncgroup.common.repository/-user-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [errorMsg](error-msg.md) | `var errorMsg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [userData](user-data.md) | `val userData: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [authLogin](auth-login.md) | `fun authLogin(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
