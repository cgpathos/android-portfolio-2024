package today.pathos.android.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import today.pathos.android.portfolio.presentation.viewmodel.state.MainEffectProvider

abstract class BaseViewModel(
    private val mainEffectProvider: MainEffectProvider,
) : ViewModel() {

    protected fun launchWithMainState(
        errorCallback: ((e: Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit,
    ) {
        viewModelScope.launch {
            try {
                mainEffectProvider.loading()
                block()
                mainEffectProvider.idle()
            } catch (e: Throwable) {
                e.printStackTrace()
                mainEffectProvider.error(e, errorCallback)
            }
        }
    }

    fun idle() = mainEffectProvider.idle()

    fun loading() = mainEffectProvider.loading()

    fun error(e: Throwable) = mainEffectProvider.error(e)
}
