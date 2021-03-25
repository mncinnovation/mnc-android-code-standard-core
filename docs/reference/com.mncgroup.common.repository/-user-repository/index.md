[reference](../../index.md) / [com.mncgroup.common.repository](../index.md) / [UserRepository](./index.md)

# UserRepository

`interface UserRepository`

an interface UserRepository

### Functions

| Name | Summary |
|---|---|
| [clearUser](clear-user.md) | `abstract fun clearUser(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Function to remove user data from app |
| [getUserLiveData](get-user-live-data.md) | `abstract fun getUserLiveData(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`>>`<br>return live data of user model |
| [updateUser](update-user.md) | `abstract fun updateUser(userModel: `[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`>>`<br>Function to update user data |

### Inheritors

| Name | Summary |
|---|---|
| [UserRepositoryImpl](../-user-repository-impl/index.md) | `class UserRepositoryImpl : `[`UserRepository`](./index.md)<br>Class implementation of [UserRepository](./index.md) |
