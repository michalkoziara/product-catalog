package ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import util.Constants


@Composable
fun Dropdown(items: List<String> = listOf(), onChange: (String) -> Unit, modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(35.dp)
                .background(color = Color(Constants.LIGHT_BLUE), shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
        ) {
            Column(
                modifier = Modifier.weight(10f)
                    .height(35.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = selectedItem,
                    style = MaterialTheme.typography.button.copy(
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "ArrowDropDown",
                    modifier = Modifier.size(128.dp),
                )
            }

        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { _, s ->
                DropdownMenuItem(onClick = {
                    selectedItem = s
                    onChange(s)
                    expanded = false
                }) {
                    Text(text = s)
                }
            }
        }
    }
}