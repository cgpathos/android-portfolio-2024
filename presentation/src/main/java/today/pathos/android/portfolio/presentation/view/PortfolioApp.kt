package today.pathos.android.portfolio.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import today.pathos.android.portfolio.presentation.state.PortfolioAppState
import today.pathos.android.portfolio.presentation.state.rememberPortfolioAppState
import today.pathos.android.portfolio.presentation.viewmodel.state.ActionEffect

@Composable
fun PortfolioApp(
    windowSizeClass: WindowSizeClass,
    closeApp: () -> Unit,
    actionEffect: ActionEffect,
    appState: PortfolioAppState = rememberPortfolioAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            PortfolioNavHost(
                appState = appState,
                closeApp = closeApp
            )
        }

        LaunchedEffect(actionEffect) {
            when (actionEffect) {
                ActionEffect.Idle -> {}
                is ActionEffect.NavigateTo -> appState.navController.navigateTo(
                    route = actionEffect.postDest.route,
                    cleanHistory = actionEffect.cleanHistory,
                )
            }
        }
    }
}
