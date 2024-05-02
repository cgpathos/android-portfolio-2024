package today.pathos.android.portfolio.presentation.viewmodel.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActionEffectProvider @Inject constructor(
) {
    private val _actionEffect = MutableStateFlow<ActionEffect>(ActionEffect.Idle)
    val actionEffect = _actionEffect.asStateFlow()

    fun tryAction(action: ActionEffect) {
        _actionEffect.tryEmit(action)
    }

    companion object {
        const val TAG = "MainEffectProvider"
    }
}
