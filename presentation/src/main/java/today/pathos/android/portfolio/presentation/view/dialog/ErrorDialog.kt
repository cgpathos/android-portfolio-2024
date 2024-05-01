package today.pathos.android.portfolio.presentation.view.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import today.pathos.android.portfolio.presentation.R
import today.pathos.android.portfolio.presentation.state.PortfolioAppState
import today.pathos.android.portfolio.presentation.view.Screens.CharacterInfo
import today.pathos.android.portfolio.presentation.view.Screens.Main
import today.pathos.android.portfolio.presentation.view.Screens.NavigateUp
import today.pathos.android.portfolio.presentation.view.Screens.Splash
import today.pathos.android.portfolio.presentation.view.screen.CharacterInfoRoute
import today.pathos.android.portfolio.presentation.view.screen.MainRoute
import today.pathos.android.portfolio.presentation.view.screen.SplashRoute
import today.pathos.android.portfolio.presentation.view.theme.Typography

@Composable
fun ErrorDialog(
    errorMsg: String?,
    onDismiss: () -> Unit = {},
) {
    if (errorMsg != null) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = Typography.titleMedium
                )
            },
            text = {
                Text(
                    text = errorMsg,
                    style = Typography.bodyLarge
                )
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_confirm),
                        style = Typography.bodyMedium
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}
