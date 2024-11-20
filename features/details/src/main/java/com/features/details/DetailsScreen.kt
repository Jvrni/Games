package com.features.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.core.designsystem.R
import com.core.designsystem.theme.Colors
import com.core.designsystem.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailsScreen(state: DetailsContract.State, event: (DetailsContract.Event) -> Unit) {
    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (box, edit, column) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(box) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        ) {
            GlideImage(
                imageModel = Uri.parse(state.game?.image ?: ""),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .shadow(2.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Transparent, RoundedCornerShape(10.dp)),
                imageOptions = ImageOptions(contentScale = ContentScale.Crop)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Black.copy(alpha = 0.2f),
                                Color.Black.copy(alpha = 0.3f),
                                Color.Black.copy(alpha = 0.7f),
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
            )

            IconButton(
                modifier = Modifier.padding(top = 34.dp, start = 4.dp),
                onClick = { event.invoke(DetailsContract.Event.OnBack) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    tint = Color.White,
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = state.game?.title ?: "",
                    style = Typography.labelLarge.copy(fontSize = 32.sp),
                    color = Color.White
                )

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(Color.White, shape = CircleShape)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Colors.outline.copy(0.2f)
                        )
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Transparent, shape = CircleShape)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_user_filled),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = state.game?.creator ?: "",
                        style = Typography.labelSmall.copy(fontSize = 12.sp),
                        color = Color.Black
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(box.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .background(Colors.background, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))

        ) {
            Text(
                modifier = Modifier.padding(top = 30.dp, start = 16.dp),
                text = "Description",
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.tertiary
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                text = state.game?.description ?: "",
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.outline
            )

            Text(
                modifier = Modifier.padding(top = 10.dp, start = 16.dp),
                text = "Category",
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.tertiary
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                text = state.game?.genre ?: "",
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.outline
            )

            Text(
                modifier = Modifier.padding(top = 10.dp, start = 16.dp),
                text = "Release date",
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.tertiary
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                text = state.game?.date ?: "",
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.outline
            )

            Row(
                modifier = Modifier.padding(top = 30.dp, start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Click ",
                    style = Typography.labelSmall.copy(fontSize = 14.sp),
                    color = Colors.outline
                )

                Text(
                    modifier = Modifier.clickable {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(state.game?.url ?: "")
                        )
                        context.startActivity(urlIntent)
                    },
                    text = "here ",
                    style = Typography.labelSmall.copy(fontSize = 14.sp),
                    color = Colors.tertiary
                )

                Text(
                    text = "to visit website",
                    style = Typography.labelSmall.copy(fontSize = 14.sp),
                    color = Colors.outline
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 60.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable { event.invoke(DetailsContract.Event.OnShowDeleteBottomSheet(true)) },
                text = "Delete",
                style = Typography.labelSmall.copy(fontSize = 16.sp),
                color = Colors.error
            )
        }

        IconButton(
            modifier = Modifier
                .constrainAs(edit) {
                    top.linkTo(column.top)
                    bottom.linkTo(column.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 16.dp)
                .shadow(1.dp, CircleShape)
                .background(Colors.background, CircleShape),
            onClick = { event.invoke(DetailsContract.Event.OnShowEditBottomSheet(true)) }
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = "")
        }

        if (state.showEditBottomSheet) {
            EditTitleBottomSheet(state = state, event = event)
        }

        if (state.showDeleteBottomSheet) {
            DeleteBottomSheet(state = state, event = event)
        }
    }
}

