package today.pathos.android.portfolio.presentation.view.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import today.pathos.android.portfolio.presentation.R
import today.pathos.android.portfolio.presentation.view.theme.Typography

@Composable
fun CharacterInfo(
    guildName: String?,
    serverName: String,
    characterImage: String,
    characterName: String,
    level: Int,
    jobGrowName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(200.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(characterImage)
                .allowHardware(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
        )

        guildName?.let {
            Text(
                text = stringResource(id = R.string.lbl_guild_name, it),
                color = Color.Cyan,
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
                modifier = Modifier
            )
        }

        Text(
            text = stringResource(id = R.string.lbl_server_character_name, serverName, characterName),
            color = Color.White,
            textAlign = TextAlign.Center,
            style = Typography.bodyMedium,
            modifier = Modifier
        )

        Text(
            text = stringResource(id = R.string.lbl_level_job, level, jobGrowName),
            color = Color.White,
            textAlign = TextAlign.Center,
            style = Typography.bodyMedium,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterInfoPreview() {
    CharacterInfo(
        guildName = "길드이름",
        serverName = "서버이름",
        characterImage = "",
        characterName = "캐릭터이름",
        level = 100,
        jobGrowName = "클래스이름",
    )
}
