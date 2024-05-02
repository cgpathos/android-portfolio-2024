package today.pathos.android.portfolio.presentation.viewmodel.state

import today.pathos.android.portfolio.presentation.viewmodel.Screens


sealed class ActionEffect {
    data object Idle : ActionEffect()
    class NavigateTo(val postDest: Screens, val cleanHistory: Boolean = false) : ActionEffect()
}
