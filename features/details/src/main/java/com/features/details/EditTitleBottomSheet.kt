package com.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.TextField
import com.core.designsystem.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTitleBottomSheet(state: DetailsContract.State, event: (DetailsContract.Event) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val text = remember { mutableStateOf("") }

    LaunchedEffect(state.game) {
        text.value = state.game?.title ?: ""
    }

    ModalBottomSheet(
        onDismissRequest = {
            event.invoke(DetailsContract.Event.OnShowEditBottomSheet(false))
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
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.align(Alignment.End), onClick = {
                event.invoke(DetailsContract.Event.OnShowEditBottomSheet(false))
            }) {
                Icon(
                    painter = painterResource(id = com.core.designsystem.R.drawable.ic_close),
                    contentDescription = ""
                )
            }

            TextField(
                modifier = Modifier
                    .padding(top = 6.dp)
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
                height = 54.dp,
                placeHolder = "",
                label = "",
                text = text,
                shadow = 1.dp,
                keyboardActions = KeyboardActions(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onValueChanged = { value -> text.value = value }
            )

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp),
                onClick = {
                    event.invoke(DetailsContract.Event.OnSave(text.value))
                }) {

                Text(stringResource(R.string.edit_label_save))
            }
        }
    }
}