[reference](../../index.md) / [com.mncgroup.common.repository](../index.md) / [UserRepositoryImpl](./index.md)

# UserRepositoryImpl

`class UserRepositoryImpl : `[`UserRepository`](../-user-repository/index.md)

Class implementation of [UserRepository](../-user-repository/index.md)

### Parameters

`userAccessDAO` - user access DAO room database

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UserRepositoryImpl(userAccessDAO: `[`UserAccessDAO`](../-user-access-d-a-o/index.md)`)`<br>Class implementation of [UserRepository](../-user-repository/index.md) |

### Functions

| Name | Summary |
|---|---|
| [clearUser](clear-user.md) | `fun clearUser(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Function to remove user data from app |
| [getUserLiveData](get-user-live-data.md) | `fun getUserLiveData(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`>>`<br>return live data of user model |
| [updateUser](update-user.md) | `fun updateUser(userModel: `[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`UserModel`](../../com.mncgroup.common.model/-user-model/index.md)`>>`<br>Function to update user data |
