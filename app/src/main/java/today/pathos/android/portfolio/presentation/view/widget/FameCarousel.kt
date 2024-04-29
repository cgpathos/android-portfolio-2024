package today.pathos.android.portfolio.presentation.view.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.presentation.view.item.FameCarouselItem
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FameCarousel(
    fameList: List<Character>,
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
) {
    Box(
        modifier = modifier
            .background(color = Color.Black.copy(alpha = 0.1f))
    ) {
        if (fameList.isNotEmpty()) {
            val state = rememberPagerState(
                pageCount = { fameList.size },
                initialPage = initialPage
            )

            HorizontalPager(
                state = state,
            ) { page ->
                val character = fameList[page]

                FameCarouselItem(
                    rank = (page + 1).toString(),
                    characterImage = character.characterImage,
                    characterName = character.characterName,
                    level = character.level,
                    jobGrowName = character.jobGrowName,
                    fame = character.fame,
                    modifier = Modifier
                        .graphicsLayer {
                            // Calculate the absolute offset for the current page from the
                            // scroll position. We use the absolute value which allows us to mirror
                            // any effects for both directions
                            val pageOffset = (
                                (state.currentPage - page) + state
                                    .currentPageOffsetFraction
                                ).absoluteValue

                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                start = 0.1f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black.copy(alpha = 0.4f))
            ) {
                Text(
                    text = "${state.currentPage + 1} / ${fameList.size}",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
