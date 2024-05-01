package today.pathos.android.portfolio.core

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import today.pathos.android.portfolio.presentation.view.PortfolioApp
import today.pathos.android.portfolio.presentation.view.theme.TemplateAndroidTheme
import today.pathos.android.portfolio.presentation.viewmodel.state.MainEffectProvider
import today.pathos.android.portfolio.presentation.viewmodel.state.MainUiEffect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainEffectProvider: MainEffectProvider

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainEffect by mainEffectProvider.mainEffect.collectAsState()
            val actionEffect by mainEffectProvider.actionEffect.collectAsState()

            TemplateAndroidTheme {
                PortfolioApp(
                    windowSizeClass = calculateWindowSizeClass(activity = this),
                    closeApp = { finish() },
                    mainEffect = mainEffect,
                    actionEffect = actionEffect
                )
            }
        }
    }
}
