package com.core.designsystem.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.R
import com.core.designsystem.theme.Colors
import com.core.designsystem.theme.GamesTheme
import com.core.designsystem.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GameCard(entity: GameCardEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White, containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                imageModel = Uri.parse(entity.image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .shadow(2.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Transparent, RoundedCornerShape(10.dp)),
                imageOptions = ImageOptions(contentScale = ContentScale.Crop)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                    text = entity.title,
                    style = Typography.labelLarge.copy(fontSize = 14.sp),
                    color = Colors.tertiary
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = entity.description,
                    style = Typography.labelSmall.copy(fontSize = 12.sp),
                    color = Colors.outline
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
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
                            text = entity.creator,
                            style = Typography.labelSmall.copy(fontSize = 12.sp),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

data class GameCardEntity(
    val image: String,
    val title: String,
    val description: String,
    val creator: String,
    val genre: String,
    val date: String
)

@Preview
@Composable
private fun PrevEventCardV() {
    GamesTheme {
        GameCard(GameCardEntity("", "", "", "", "", "")) {}
    }
}