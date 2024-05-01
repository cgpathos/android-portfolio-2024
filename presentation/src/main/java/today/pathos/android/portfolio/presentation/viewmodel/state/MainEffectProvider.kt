package today.pathos.android.portfolio.presentation.viewmodel.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainEffectProvider @Inject constructor(
) {
    private val _mainEffect = MutableStateFlow<MainUiEffect>(MainUiEffect.Idle)
    val mainEffect = _mainEffect.asStateFlow()

    private val _actionEffect = MutableStateFlow<ActionEffect>(ActionEffect.Idle)
    val actionEffect = _actionEffect.asStateFlow()

    fun idle() = _mainEffect.tryEmit(MainUiEffect.Idle)
    fun loading() = _mainEffect.tryEmit(MainUiEffect.Loading)
    fun error(
        e: Throwable,
        callback: ((e: Throwable) -> Unit)? = null,
    ) = _mainEffect.tryEmit(
        MainUiEffect.Error(e) {
            _mainEffect.tryEmit(MainUiEffect.Idle)
            callback?.invoke(it)
        }
    )

    fun tryAction(action: ActionEffect) {
        _actionEffect.tryEmit(action)
    }

    companion object {
        const val TAG = "MainEffectProvider"
    }
}
