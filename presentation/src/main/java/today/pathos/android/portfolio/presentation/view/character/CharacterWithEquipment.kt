package today.pathos.android.portfolio.presentation.view.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.presentation.R
import today.pathos.android.portfolio.presentation.viewmodel.ItemSlotInfo

@Composable
fun CharacterWithEquipment(
    characterInfo: Character,
    armorList: List<ItemSlotInfo>,
    accessoryList: List<ItemSlotInfo>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {

        with(characterInfo) {
            CharacterInfo(
                guildName = guildName,
                serverName = serverId,
                characterImage = stringResource(id = R.string.character_image_url, serverId, characterId, 3),
                characterName = characterName,
                level = level,
                jobGrowName = jobGrowName,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }

        EquipmentArmorInfo(
            armorList = armorList,
            modifier = Modifier
                .align(Alignment.TopStart)
        )

        EquipmentAccessoryInfo(
            armorList = accessoryList,
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterWithEquipmentPreview() {
    CharacterWithEquipment(
        characterInfo = Character(
            serverId = "서버아이디",
            characterId = "",
            characterName = "캐릭터이름",
            level = 50,
            jobId = "",
            jobGrowId = "클래스이름",
            jobName = "",
            jobGrowName = "",
            fame = 0,
            adventureName = "",
            guildId = "",
            guildName = "길드이름",
        ),
        armorList = listOf(),
        accessoryList = listOf()
    )
}
