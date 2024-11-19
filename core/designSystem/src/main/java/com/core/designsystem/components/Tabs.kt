package com.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.core.designsystem.R
import com.core.designsystem.theme.Colors
import com.core.designsystem.theme.GamesTheme
import com.core.designsystem.theme.Typography
import com.core.designsystem.theme.black000000

@ExperimentalAnimationApi
@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    list: List<TabsEntity>,
    action: (selected: String) -> Unit
) {
    val selectedIndex = rememberSaveable { mutableIntStateOf(0) }
    action.invoke(list[selectedIndex.intValue].title)

    ScrollableTabRow(
        selectedTabIndex = selectedIndex.intValue,
        containerColor = Color.White,
        modifier = modifier
            .padding(top = 8.dp)
            .padding(bottom = 8.dp),
        edgePadding = 16.dp,
        indicator = { tabPositions: List<TabPosition> ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex.intValue])
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Colors.primary)
                    .zIndex(1f)
                    .padding(horizontal = 12.dp)
            )
        },
        divider = {}
    ) {
        list.forEachIndexed { index, item ->
            val selected = selectedIndex.intValue == index
            val colorText = if (selected) Color.White.copy(alpha = 0.9f) else black000000
            val background = if (selected) Color.Transparent else Color.White

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(background, shape = RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = null,
                        indication = rememberRipple(color = Color.Transparent)
                    ) {
                        selectedIndex.intValue = index
                        action.invoke(item.title)
                    }
                    .zIndex(2f)
                    .border(
                        1.dp,
                        shape = RoundedCornerShape(30.dp),
                        color = if (selected) Colors.primary else Colors.outline.copy(0.2f)
                    )
                    .padding(horizontal = 4.dp)
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (item.icon != null)
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 5.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                    )

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = item.title,
                    style = Typography.labelLarge,
                    color = colorText
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun PreviewTabs() {
    GamesTheme {
        Tabs(
            list = listOf(
                TabsEntity(
                    title = "All",
                    icon = R.drawable.ic_all_inclusive
                ),
                TabsEntity(
                    title = "User",
                    icon = R.drawable.ic_user_filled
                )
            )
        ) { selected -> }
    }
}

data class TabsEntity(
    val title: String,
    @DrawableRes val icon: Int? = null
)