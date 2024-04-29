package today.pathos.android.portfolio.presentation.view.character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import today.pathos.android.portfolio.R
import today.pathos.android.portfolio.presentation.view.theme.Typography

@Composable
fun ItemSlot(
    reinforce: Int?,
    itemImage: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.0f)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Yellow,
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(itemImage)
                .allowHardware(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        reinforce?.let {
            Text(
                text = stringResource(id = R.string.lbl_reinforce, it),
                color = Color.Magenta,
                textAlign = TextAlign.Center,
                style = Typography.labelSmall,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .background(color = Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 2.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemSlotPreview() {
    ItemSlot(
        reinforce = 15,
        itemImage = ""
    )
}
