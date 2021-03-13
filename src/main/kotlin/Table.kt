import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Table(computers: List<Computer> = emptyList()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        stickyHeader {
            Header()
        }
        itemsIndexed(computers) { index, computer ->
            Row {
                HeaderCell(
                    modifier = Modifier.weight(0.5f),
                    text = (index + 1).toString(),
                    height = 60.dp
                )
                listOf(
                    computer.manufacturerName,
                    computer.diagonalScreenSize,
                    computer.screenResolution,
                    computer.screenSurfaceType,
                    computer.touchscreenFlag,
                    computer.cpu,
                    computer.numberOfCpuCores,
                    computer.clockFrequency,
                    computer.ram,
                    computer.discSize,
                    computer.discType,
                    computer.gpu,
                    computer.gpuMemory,
                    computer.operatingSystem,
                    computer.physicalDriveType
                ).forEach { value -> Cell(modifier = Modifier.weight(1f, fill = true), text = value) }
            }
        }
    }
}

@Composable
fun Header() {
    Row {
        HeaderCell(modifier = Modifier.weight(0.5f), text = "LP.")
        listOf(
            "Nazwa producenta",
            "Przekątna ekranu",
            "Rozdzielczość ekranu",
            "Rodzaj powierzchni ekranu",
            "Czy ekran jest dotykowy",
            "Nazwa procesora",
            "Liczba rdzeni fizycznych",
            "Prędkość taktowania MHz",
            "Wielkość pamięci RAM",
            "Pojemność dysku",
            "Rodzaj dysku",
            "Nazwa układu graficznego",
            "Pamięć układu graficznego",
            "Nazwa systemu operacyjnego",
            "Rodzaj napędu fizycznego w komputerze "
        ).forEach { label -> HeaderCell(modifier = Modifier.weight(1f), text = label) }
    }
}

@Composable
fun HeaderCell(
    modifier: Modifier,
    text: String,
    height: Dp = 85.dp
) {
    Card(
        modifier = modifier.height(height = height),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(width = 0.5.dp, color = Color(0xDD000000))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text, textAlign = TextAlign.Center)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Cell(
    modifier: Modifier,
    text: String,
    height: Dp = 60.dp
) {
    var isEditable: Boolean by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.height(height = height),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(width = 0.5.dp, color = Color(0xDD000000))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.combinedClickable(
                onClick = {
                    isEditable = !isEditable
                },
            )
        ) {
            if (isEditable)
                AutoFocusingText(text, onClick = { isEditable = !isEditable })
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoFocusingText(text: String, onClick: () -> Unit) {
    var value by mutableStateOf(TextFieldValue(text))

    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        value = value,
        onValueChange = { value = it },
        textStyle = MaterialTheme.typography.body1.copy(
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.focusRequester(focusRequester)
            .combinedClickable(
                onClick = {
                    onClick()
                }
            ),
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}