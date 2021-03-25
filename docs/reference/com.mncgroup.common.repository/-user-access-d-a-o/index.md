[reference](../../index.md) / [com.mncgroup.common.repository](../index.md) / [UserAccessDAO](./index.md)

# UserAccessDAO

`interface UserAccessDAO`

### Functions

| Name | Summary |
|---|---|
| [getUserData](get-user-data.md) | `abstract fun getUserData(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`>>` |
| [insertDataUser](insert-data-user.md) | `abstract suspend fun insertDataUser(userModel: `[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeUser](remove-user.md) | `abstract suspend fun removeUser(userModel: `[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
