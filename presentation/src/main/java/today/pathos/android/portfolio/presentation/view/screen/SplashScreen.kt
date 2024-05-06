package today.pathos.android.portfolio.presentation.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import today.pathos.android.portfolio.presentation.R
import today.pathos.android.portfolio.presentation.view.dialog.ErrorDialog
import today.pathos.android.portfolio.presentation.view.theme.Typography
import today.pathos.android.portfolio.presentation.viewmodel.SplashUiState
import today.pathos.android.portfolio.presentation.viewmodel.SplashViewModel

@Composable
fun SplashRoute(
    navigateToMain: () -> Unit,
    closeApp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    SplashScreen(
        state = state,
        navigateToMain = navigateToMain,
        closeApp = closeApp,
        modifier = modifier
    )
}

@Composable
fun SplashScreen(
    state: SplashUiState,
    navigateToMain: () -> Unit,
    closeApp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(state) {
        snapshotFlow { state }
            .distinctUntilChanged()
            .flowWithLifecycle(lifecycle)
            .collect {
                when (state) {
                    SplashUiState.Init -> {}
                    is SplashUiState.Error -> {
                        closeApp()
                    }

                    SplashUiState.NavigateToMain -> {
                        navigateToMain()
                    }
                }
            }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Splash Screen",
            style = Typography.displayMedium,
            modifier = Modifier.align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.neople_open_api_logo),
            contentDescription = "Neople Open API Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(100.dp)
                .aspectRatio(1.5f)
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        state = SplashUiState.Init,
        navigateToMain = {},
        closeApp = {}
    )
}
