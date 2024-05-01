package today.pathos.android.portfolio.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import today.pathos.android.portfolio.presentation.state.PortfolioAppState
import today.pathos.android.portfolio.presentation.view.Screens.CharacterInfo
import today.pathos.android.portfolio.presentation.view.Screens.Main
import today.pathos.android.portfolio.presentation.view.Screens.NavigateUp
import today.pathos.android.portfolio.presentation.view.Screens.Splash
import today.pathos.android.portfolio.presentation.view.screen.CharacterInfoRoute
import today.pathos.android.portfolio.presentation.view.screen.MainRoute
import today.pathos.android.portfolio.presentation.view.screen.SplashRoute

enum class Screens(
    val route: String,
) {
    NavigateUp(route = "navigate_up"), // 뒤로가기 처리용
    Splash(route = "splash"),
    Main(route = "main"),
    CharacterInfo(route = "character_info/{serverId}/{characterId}")
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

fun NavHostController.navigateCharacterInfo(serverId: String, characterId: String) = navigateTo(
    route = CharacterInfo.route
        .replace("{serverId}", serverId)
        .replace("{characterId}", characterId),
)

@Composable
fun PortfolioNavHost(
    appState: PortfolioAppState,
    closeApp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = Main.route, // fixme: Splash 구현 끝나면 추가
        modifier = modifier
    ) {
        composable(
            route = Splash.route
        ) {
            SplashRoute()
        }

        composable(
            route = Main.route
        ) {
            MainRoute(
                onClickCharacterInfo = { serverId, characterId ->
                    navController.navigateCharacterInfo(
                        serverId,
                        characterId
                    )
                }
            )
        }

        composable(
            route = CharacterInfo.route,
            arguments = listOf(
                navArgument("serverId") { type = NavType.StringType },
                navArgument("characterId") { type = NavType.StringType },
            )
        ) {
            CharacterInfoRoute()
        }
    }
}
