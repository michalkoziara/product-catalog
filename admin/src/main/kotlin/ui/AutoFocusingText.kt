package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoFocusingText(
    text: String,
    onClick: () -> Unit,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val value by mutableStateOf(text)

    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        value = value,
        onValueChange = onTextChange,
        textStyle = MaterialTheme.typography.body1.copy(
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        ),
        modifier = modifier.focusRequester(focusRequester)
            .combinedClickable(onClick = onClick)
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}