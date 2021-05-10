package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import util.Constants

@Composable
fun DataCell(
    text: String,
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(height = 60.dp)
            .border(width = 0.5.dp, color = Color(Constants.BLACK_85))
    ) {
        Text(
            text,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            ),
        )
    }
}