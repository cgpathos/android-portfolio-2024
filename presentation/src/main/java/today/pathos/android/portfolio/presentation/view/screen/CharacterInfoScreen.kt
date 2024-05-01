package today.pathos.android.portfolio.presentation.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import today.pathos.android.portfolio.presentation.view.character.CharacterWithEquipment
import today.pathos.android.portfolio.presentation.viewmodel.CharacterInfoUiState
import today.pathos.android.portfolio.presentation.viewmodel.CharacterInfoViewModel

@Composable
fun CharacterInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterInfoViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    CharacterInfoScreen(
        state = state,
        modifier = modifier
    )
}

@Composable
fun CharacterInfoScreen(
    state: CharacterInfoUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        CharacterWithEquipment(
            characterInfo = state.characterInfo,
            armorList = state.armorList,
            accessoryList = state.accessoryList
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterInfoScreenPreview() {
    CharacterInfoScreen(
        state = CharacterInfoUiState.EMPTY_STATE
    )
}
