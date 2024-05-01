package today.pathos.android.portfolio.presentation.state

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun rememberPortfolioAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): PortfolioAppState {
    return remember(
        windowSizeClass,
        navController
    ) {
        PortfolioAppState(
            windowSizeClass,
            navController
        )
    }
}

@Stable
class PortfolioAppState(
    val windowSizeClass: WindowSizeClass,
    val navController: NavHostController,
) {

}
