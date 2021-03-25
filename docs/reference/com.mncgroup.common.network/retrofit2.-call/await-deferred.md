[reference](../../index.md) / [com.mncgroup.common.network](../index.md) / [retrofit2.Call](index.md) / [awaitDeferred](./await-deferred.md)

# awaitDeferred

`fun <T : `[`BaseResponseModel`](../../com.mncgroup.common.model/-base-response-model/index.md)`> Call<`[`T`](await-deferred.md#T)`>.awaitDeferred(coroutineScope: CoroutineScope = GlobalScope): Deferred<Result<`[`T`](await-deferred.md#T)`>>`
`fun <T : `[`BaseResponseModel`](../../com.mncgroup.common.model/-base-response-model/index.md)`, R : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> Call<`[`T`](await-deferred.md#T)`>.awaitDeferred(coroutineScope: CoroutineScope = GlobalScope, transform: `[`T`](await-deferred.md#T)`.() -> `[`R`](await-deferred.md#R)`): Deferred<Result<`[`R`](await-deferred.md#R)`>>`