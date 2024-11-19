package com.features.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.core.designsystem.theme.Colors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(state: SplashContract.State, event: (SplashContract.Event) -> Unit) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))

    Box(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(0.55f),
            composition = composition,
            iterations = Int.MAX_VALUE,
            clipSpec = LottieClipSpec.Progress(0f, 1f)
        )

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 20.dp),
            visible = state.error
        ) {
            Snackbar(
                modifier = Modifier.padding(top = 40.dp),
                shape = RoundedCornerShape(16.dp),
                containerColor = Colors.errorContainer
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = com.core.designsystem.R.drawable.ic_warning),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                            .weight(1f),
                        text = "Please try again later",
                        textAlign = TextAlign.Start
                    )

                    Box(
                        modifier = Modifier
                            .background(Color.Transparent, shape = CircleShape)
                            .clip(CircleShape)
                            .clickable { event.invoke(SplashContract.Event.OnRefresh) }
                            .padding(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = com.core.designsystem.R.drawable.ic_reload),
                            contentDescription = ""
                        )
                    }
                }

                LaunchedEffect(key1 = Unit) {
                    delay(2000)
                }
            }
        }
    }
}