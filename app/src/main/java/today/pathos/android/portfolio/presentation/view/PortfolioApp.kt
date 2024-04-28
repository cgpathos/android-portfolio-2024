package today.pathos.android.portfolio.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import today.pathos.android.portfolio.presentation.state.PortfolioAppState
import today.pathos.android.portfolio.presentation.state.rememberPortfolioAppState

@Composable
fun PortfolioApp(
    windowSizeClass: WindowSizeClass,
    closeApp: () -> Unit,
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
    }
}
