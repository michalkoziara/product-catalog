package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import util.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditableCell(
    text: String,
    onTextChange: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier,
) {
    var isEditable: Boolean by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(height = 60.dp)
            .border(width = 0.5.dp, color = Color(Constants.BLACK_85))
            .background(color = backgroundColor)
            .combinedClickable(onClick = { isEditable = !isEditable })
    ) {
        if (isEditable)
            AutoFocusingText(
                text,
                onClick = { isEditable = !isEditable },
                onTextChange = onTextChange,
            )
        else
            Text(
                text,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                ),
            )
    }
}