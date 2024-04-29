package today.pathos.android.portfolio.presentation.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import today.pathos.android.portfolio.presentation.view.item.FameItem
import today.pathos.android.portfolio.presentation.view.widget.FameCarousel
import today.pathos.android.portfolio.presentation.viewmodel.MainUiState
import today.pathos.android.portfolio.presentation.viewmodel.MainViewModel

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    MainScreen(
        state = state,
        modifier = modifier
    )
}

@Composable
fun MainScreen(
    state: MainUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        FameCarousel(
            fameList = state.fameTop5List,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )

        LazyColumn {
            items(state.fameList) {
                FameItem(
                    characterImage = it.characterImage,
                    characterName = it.characterName,
                    level = it.level,
                    jobGrowName = it.jobGrowName,
                    fame = it.fame,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        state = MainUiState.EMPTY_STATE
    )
}
