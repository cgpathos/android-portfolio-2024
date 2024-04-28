package today.pathos.android.portfolio.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint
import today.pathos.android.portfolio.presentation.view.PortfolioApp
import today.pathos.android.portfolio.presentation.view.theme.TemplateAndroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TemplateAndroidTheme {
                PortfolioApp(
                    windowSizeClass = calculateWindowSizeClass(activity = this),
                    closeApp = { finish() }
                )
            }
        }
    }
}
