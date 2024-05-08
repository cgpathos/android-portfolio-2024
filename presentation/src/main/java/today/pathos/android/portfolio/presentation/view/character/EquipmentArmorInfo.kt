package today.pathos.android.portfolio.presentation.view.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import today.pathos.android.portfolio.presentation.R
import today.pathos.android.portfolio.presentation.viewmodel.ItemSlotInfo

@Composable
fun EquipmentArmorInfo(
    armorList: List<ItemSlotInfo>,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyGridState()

    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .width(100.dp)
    ) {
        items(
            items = armorList,
        ) { armor ->
            ItemSlot(
                reinforce = armor.reinforce,
                itemImage = stringResource(id = R.string.item_image_url, armor.itemId)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipmentArmorInfoPreview() {
    EquipmentArmorInfo(
        armorList = listOf(
            ItemSlotInfo("", null),
            ItemSlotInfo("", 15),
            ItemSlotInfo("", 3),
            ItemSlotInfo("", 2),
            ItemSlotInfo("", 5),
            ItemSlotInfo("", null),
        ),
    )
}
