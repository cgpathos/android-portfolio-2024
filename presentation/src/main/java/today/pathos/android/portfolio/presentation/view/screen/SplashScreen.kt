package today.pathos.android.portfolio.presentation.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import today.pathos.android.portfolio.presentation.view.theme.Typography

@Composable
fun SplashRoute(
    modifier: Modifier = Modifier,
) {
    SplashScreen(
        modifier = modifier
    )
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Splash Screen",
            style = Typography.displayLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
