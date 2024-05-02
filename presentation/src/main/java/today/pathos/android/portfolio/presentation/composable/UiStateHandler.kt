package today.pathos.android.portfolio.presentation.composable

import android.util.Log
import androidx.compose.runtime.Composable
import today.pathos.android.portfolio.common.result.Result
import today.pathos.android.portfolio.presentation.BuildConfig
import today.pathos.android.portfolio.presentation.view.dialog.ErrorDialog
import today.pathos.android.portfolio.presentation.view.dialog.LoadingDialog

@Composable
fun <T> UiStateHandler(
    result: Result<T>,
    content: @Composable (state: T) -> Unit,
) {
    when (result) {
        Result.Loading -> LoadingDialog()
        is Result.Success -> {
            content(result.data)
        }

        is Result.Error -> {
            ErrorDialog(
                errorMsg = result.e.message,
                onDismiss = { result.callback?.invoke(result.e) }
            )
        }
    }.also {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "------UiState::$result")
        }
    }
}

const val TAG = "UiStateHandler"
