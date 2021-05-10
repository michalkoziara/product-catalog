package ui

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import services.generated.Computer
import util.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComputerTable(
    computers: List<Computer> = emptyList(),
    modifier: Modifier = Modifier
) {
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
                    itemCount = computers.size,
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
                .border(width = 1.dp, color = Color(Constants.BLACK_85)),
        ) {
            stickyHeader {
                Header()
            }
            itemsIndexed(computers) { index, computer ->
                Row {
                    Cell(
                        text = (index + 1).toString(),
                        modifier = Modifier.weight(0.5f).height(60.dp),
                    )
                    DataCell(
                        text = computer.manufacturerName,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.diagonalScreenSize,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.screenResolution,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.screenSurfaceType,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.touchscreenFlag,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.cpu,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.numberOfCpuCores,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.clockFrequency,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.ram,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.discSize,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.discType,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.gpu,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.gpuMemory,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.operatingSystem,
                        modifier = Modifier.weight(1f),
                    )
                    DataCell(
                        text = computer.physicalDriveType,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Cell(modifier = Modifier.weight(0.5f), text = Constants.ORDINAL_NUMBER_LABEL)
        listOf(
            Constants.MANUFACTURER_NAME_LABEL,
            Constants.DIAGONAL_SCREEN_SIZE_LABEL,
            Constants.SCREEN_RESOLUTION_LABEL,
            Constants.SCREEN_SURFACE_TYPE_LABEL,
            Constants.TOUCHSCREEN_FLAG_LABEL,
            Constants.CPU_LABEL,
            Constants.NUMBER_OF_CPU_CORES_LABEL,
            Constants.CLOCK_FREQUENCY_LABEL,
            Constants.RAM_LABEL,
            Constants.DISC_SIZE_LABEL,
            Constants.DISC_TYPE_LABEL,
            Constants.GPU_LABEL,
            Constants.GPU_MEMORY_LABEL,
            Constants.OPERATING_SYSTEM_LABEL,
            Constants.PHYSICAL_DRIVE_TYPE_LABEL,
        ).forEach { label -> Cell(modifier = Modifier.weight(1f), text = label) }
    }
}