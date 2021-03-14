import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ManufacturerTable(numberOfComputersByManufacturer: Map<String, Int> = emptyMap(), modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyListState()
    val averageItemSize: Float by derivedStateOf {
        val items = lazyListState.layoutInfo.visibleItemsInfo
        items.sumBy { it.size }.toFloat() / (items.size - 1)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DesktopTheme(
            ScrollbarStyle(
                minimalHeight = 16.dp,
                thickness = 8.dp,
                shape = MaterialTheme.shapes.small,
                hoverDurationMillis = 300,
                unhoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.32f),
                hoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.50f)
            )
        ) {
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(
                    scrollState = lazyListState,
                    itemCount = numberOfComputersByManufacturer.size,
                    averageItemSize =
                    if (averageItemSize.isFinite() && averageItemSize > 0)
                        averageItemSize.dp
                    else
                        1.dp,
                )
            )
        }
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
                .padding(end = 10.dp)
                .border(width = 1.dp, color = Color(0xDD000000)),
        ) {
            stickyHeader {
                ManufacturerHeader()
            }
            items(numberOfComputersByManufacturer.entries.toList()) { item ->
                Row {
                    Cell(
                        text = item.key,
                        modifier = Modifier.weight(0.5f).height(40.dp),
                    )
                    Cell(
                        text = item.value.toString(),
                        modifier = Modifier.weight(0.5f).height(40.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ManufacturerHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        listOf(
            "Nazwa producenta",
            "Liczba komputerów",
        ).forEach { label -> Cell(text = label, modifier = Modifier.weight(1f).height(40.dp)) }
    }
}
