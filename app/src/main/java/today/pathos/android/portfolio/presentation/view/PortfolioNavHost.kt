package today.pathos.android.portfolio.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import today.pathos.android.portfolio.presentation.state.PortfolioAppState
import today.pathos.android.portfolio.presentation.view.Screens.NavigateUp
import today.pathos.android.portfolio.presentation.view.screen.MainRoute
import today.pathos.android.portfolio.presentation.view.screen.SplashRoute

enum class Screens(
    val route: String,
) {
    NavigateUp(route = "navigate_up"), // 뒤로가기 처리용
    Splash(route = "splash"),
    Main(route = "main"),
}

fun NavHostController.navigateTo(
    route: String,
    cleanHistory: Boolean = false,
    launchSingleTop: Boolean = false,
) {
    if (route == NavigateUp.route) {
        navigateUp()
    } else {
        navigate(
            route = route
        ) {
            if (cleanHistory) {
                popUpTo(graph.id) {
                    inclusive = true
                }
            }
            this.launchSingleTop = launchSingleTop
        }
    }
}

@Composable
fun PortfolioNavHost(
    appState: PortfolioAppState,
    closeApp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = Screens.Main.route, // fixme: Splash 구현 끝나면 추가
        modifier = modifier
    ) {
        composable(
            route = Screens.Splash.route
        ) {
            SplashRoute()
        }

        composable(
            route = Screens.Main.route
        ) {
            MainRoute()
        }

//        dialog(
//            route = OpenStoreDialog.route
//        ) {
//            OpenStoreRoute(
//                onDismiss = { navController.navigateUp() }
//            )
//        }
    }
}
