package today.pathos.android.portfolio.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val e: Throwable, val callback: ((e: Throwable) -> Unit)? = null) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(errorCallback: ((e: Throwable) -> Unit)? = null): Flow<Result<T>> =
    map<T, Result<T>> { Result.Success(it) }
        .onStart { Result.Loading }
        .catch { Result.Error(it, errorCallback) }

