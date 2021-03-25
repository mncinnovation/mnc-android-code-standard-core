[reference](../../index.md) / [com.mncgroup.common.network](../index.md) / [retrofit2.Call](index.md) / [awaitResult](./await-result.md)

# awaitResult

`suspend fun <T : `[`BaseResponseModel`](../../com.mncgroup.common.model/-base-response-model/index.md)`> Call<`[`T`](await-result.md#T)`>.awaitResult(): Result<`[`T`](await-result.md#T)`>`
`@ExperimentalCoroutinesApi suspend fun <T : `[`BaseResponseModel`](../../com.mncgroup.common.model/-base-response-model/index.md)`, R : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> Call<`[`T`](await-result.md#T)`>.awaitResult(transform: `[`T`](await-result.md#T)`.() -> `[`R`](await-result.md#R)`): Result<`[`R`](await-result.md#R)`>`

Suspend extension that allows suspend [Call](#) inside coroutine.

**Return**
sealed class [Result](#) object that can be
    casted to [Result.Ok](#) (success) or [Result.Error](#) (HTTP error) and [Result.Exception](#) (other errors)

