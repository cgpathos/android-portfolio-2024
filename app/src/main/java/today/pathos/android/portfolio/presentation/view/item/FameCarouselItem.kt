package today.pathos.android.portfolio.presentation.view.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
fun FameCarouselItem(
    rank: String,
    characterImage: String,
    characterName: String,
    level: Int,
    jobGrowName: String,
    fame: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .background(
                    color = Color.Gray.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterImage)
                    .allowHardware(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = rank,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = Typography.bodySmall,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .background(
                        color = Color.Cyan.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .wrapContentHeight(
                        align = Alignment.CenterVertically
                    )
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = characterName,
                textAlign = TextAlign.Center,
                style = Typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.level, level),
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = jobGrowName,
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.fame, fame),
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FameCarouselItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        FameCarouselItem(
            rank = "1",
            characterImage = "",
            characterName = "사용자이름",
            level = 100,
            jobGrowName = "眞 소환사",
            fame = 6700
        )
    }
}
