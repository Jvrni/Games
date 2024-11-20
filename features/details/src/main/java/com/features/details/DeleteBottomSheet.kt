package com.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.theme.Colors
import com.core.designsystem.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBottomSheet(state: DetailsContract.State, event: (DetailsContract.Event) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val text = remember { mutableStateOf("") }

    LaunchedEffect(state.game) {
        text.value = state.game?.title ?: ""
    }

    ModalBottomSheet(
        onDismissRequest = {
            event.invoke(DetailsContract.Event.OnShowDeleteBottomSheet(false))
        },
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Colors.background,
        tonalElevation = 16.dp,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .width(50.dp)
                    .height(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Colors.background)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.align(Alignment.End), onClick = {
                event.invoke(DetailsContract.Event.OnShowDeleteBottomSheet(false))
            }) {
                Icon(
                    painter = painterResource(id = com.core.designsystem.R.drawable.ic_close),
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                text = stringResource(R.string.delete_label_title_are_you_sure_you_want_to_delete),
                style = Typography.labelSmall.copy(fontSize = 14.sp),
                color = Colors.tertiary
            )

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp),
                onClick = {
                    event.invoke(DetailsContract.Event.OnDelete)
                }) {

                Text(stringResource(R.string.delete_label_confirm))
            }
        }
    }
}