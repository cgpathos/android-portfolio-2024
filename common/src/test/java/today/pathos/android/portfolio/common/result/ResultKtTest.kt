package today.pathos.android.portfolio.common.result

import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultKtTest {

    @Test
    fun asResultError() = runTest {
        flow {
            emit(1)
            throw Throwable("Some Error")
        }.asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(1), awaitItem())

                when (val error = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "Some Error",
                        error.e.message
                    )

                    Result.Loading,
                    is Result.Success,
                    -> {
                        throw IllegalStateException("Flow should have emitted an Result.Error")
                    }
                }

                awaitComplete()
            }
    }
}
