package com.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.shadow
import com.core.designsystem.theme.Colors
import com.core.designsystem.theme.Typography

@Composable
fun TextField(
    modifier: Modifier,
    shape: Shape,
    height: Dp = 60.dp,
    colors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.White,
        focusedContainerColor = Color.White,
        unfocusedIndicatorColor = Color.White,
    ),
    singleLine: Boolean = true,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    placeHolder: String,
    label: String,
    text: MutableState<String>,
    onValueChanged: (String) -> Unit = {},
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
    shadow: Dp = 3.dp
) {
    androidx.compose.material3.TextField(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .shadow(shadow, shape),
        shape = shape,
        colors = colors,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            Text(
                text = placeHolder,
                style = Typography.labelMedium,
                color = Colors.outline.copy(0.8f)
            )
        },
        label = if (label.isEmpty()) null else {
            {
                Text(
                    text = label,
                    style = Typography.labelSmall
                )
            }
        },
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChanged.invoke(it)
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun TextField(
    modifier: Modifier,
    height: Dp = 60.dp,
    shape: Shape,
    background: Color = Colors.background,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    singleLine: Boolean = true,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    placeHolder: AnnotatedString,
    text: MutableState<String>,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions
) {
    androidx.compose.material3.TextField(
        modifier = modifier
            .height(height)
            .border(
                border = BorderStroke(1.dp, Colors.scrim),
                shape = shape
            )
            .fillMaxWidth(),
        shape = shape,
        colors = colors,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            Text(
                text = placeHolder,
                style = Typography.labelSmall
            )
        },
        value = text.value,
        onValueChange = { text.value = it },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}