package com.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.R
import com.core.designsystem.components.GameCard
import com.core.designsystem.components.Tabs
import com.core.designsystem.components.TextField
import com.core.designsystem.theme.Colors
import com.core.designsystem.theme.Typography

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(state: HomeContract.State, event: (HomeContract.Event) -> Unit) {
    val text = remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Colors.background)
            ) {
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = ""
                )

                TextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .border(
                            0.3.dp,
                            shape = RoundedCornerShape(30.dp),
                            color = Colors.outline.copy(0.2f)
                        )
                        .pointerInput(Unit) { detectTapGestures(onTap = {}) },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        focusedIndicatorColor = Color.White
                    ),
                    shape = RoundedCornerShape(30.dp),
                    height = 50.dp,
                    placeHolder = "Search things to do ...",
                    label = "",
                    text = text,
                    shadow = 1.dp,
                    keyboardActions = KeyboardActions(),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.ic_search_outline),
                            contentDescription = ""
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    onValueChanged = { value -> text.value = value }
                )

                Text(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp)
                        .align(Alignment.Start),
                    text = "Categories",
                    style = Typography.labelLarge.copy(fontSize = 14.sp),
                    color = Colors.tertiary
                )

                if (state.categories.isNotEmpty())
                    Tabs(list = state.categories) { selected ->
                        event.invoke(
                            HomeContract.Event.OnFilter(
                                search = text.value,
                                category = selected
                            )
                        )
                    }
            }
        }

        items(state.list) { item ->
            GameCard(entity = item) {

            }
        }

        if (state.isEmpty)
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 68.dp, bottom = 8.dp),
                        text = "Not found",
                        style = Typography.labelLarge.copy(fontSize = 18.sp),
                        color = Colors.tertiary
                    )

                    Text(
                        text = "No games found",
                        style = Typography.labelSmall.copy(fontSize = 16.sp),
                        color = Colors.outline
                    )

                    Image(
                        modifier = Modifier.size(300.dp),
                        painter = painterResource(id = R.drawable.img_empty),
                        contentDescription = ""
                    )
                }
            }
    }
}